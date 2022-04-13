package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.constants.BugStatus;
import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.dto.ApiError;
import egroup.ag.tasktracker.dto.BugDto;
import egroup.ag.tasktracker.dto.BugRequestModel;
import egroup.ag.tasktracker.entity.BugEntity;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.exception.InvalidUserInputException;
import egroup.ag.tasktracker.repository.BugRepository;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.service.BugService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final DeveloperRepository developerRepository;

    public BugServiceImpl(BugRepository bugRepository, DeveloperRepository developerRepository) {
        this.bugRepository = bugRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public BugDto createBug(BugRequestModel bug) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        BugEntity bugEntity = modelMapper.map(bug, BugEntity.class);
        bugEntity.setStatus(BugStatus.NEW);

        if (bug.getAssignedDeveloperId() != null) {
            Optional<DeveloperEntity> developerEntity = developerRepository.findById(bug.getAssignedDeveloperId());
            if (developerEntity.isEmpty()) {
                throw InvalidUserInputException
                        .builder()
                        .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND,
                                String.valueOf(bug.getAssignedDeveloperId()))
                        ).build();
            }
            bugEntity.setAssignedDeveloper(developerEntity.get());
        }
        return modelMapper.map(bugRepository.save(bugEntity), BugDto.class);
    }

    @Override
    public List<BugDto> getAllBugs() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Type listType = new TypeToken<List<BugDto>>() {
        }.getType();
        return modelMapper.map(bugRepository.findAll(), listType);
    }

    @Override
    public BugDto getBugById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        BugDto bug = modelMapper.map(bugRepository.findById(id), BugDto.class);
        if (bug == null) {
            throw InvalidUserInputException
                    .builder()
                    .error(new ApiError(ErrorMessage.BUG_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        return bug;
    }

    @Override
    public BugDto updateBugById(long id, BugRequestModel bug) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Optional<BugEntity> bugEntity = bugRepository.findById(id);

        if (bugEntity.isEmpty()) {
            throw InvalidUserInputException
                    .builder()
                    .error(new ApiError(ErrorMessage.BUG_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
//        storyEntity.get().setName(developer.getName());
        return modelMapper.map(bugRepository.save(bugEntity.get()), BugDto.class);
    }

    @Override
    public void deleteBugById(long id) {
        Optional<BugEntity> bugEntity = bugRepository.findById(id);
        if (bugEntity.isEmpty()) {
            throw InvalidUserInputException
                    .builder()
                    .error(new ApiError(ErrorMessage.BUG_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        bugRepository.deleteById(id);
    }
}
