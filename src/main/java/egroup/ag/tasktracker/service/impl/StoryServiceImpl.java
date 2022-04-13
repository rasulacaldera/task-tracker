package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.constants.StoryStatus;
import egroup.ag.tasktracker.dto.ApiError;
import egroup.ag.tasktracker.dto.StoryDto;
import egroup.ag.tasktracker.dto.StoryRequestModel;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.entity.StoryEntity;
import egroup.ag.tasktracker.exception.InvalidUserInputException;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.repository.StoryRepository;
import egroup.ag.tasktracker.service.StoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final DeveloperRepository developerRepository;

    public StoryServiceImpl(StoryRepository storyRepository, DeveloperRepository developerRepository) {
        this.storyRepository = storyRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public StoryDto createStory(StoryRequestModel story) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        StoryEntity storyEntity = modelMapper.map(story, StoryEntity.class);
        storyEntity.setStatus(StoryStatus.NEW);

        if (story.getAssignedDeveloperId() != null) {
            Optional<DeveloperEntity> developerEntity = developerRepository.findById(story.getAssignedDeveloperId());
            if (developerEntity.isEmpty()) {
                throw InvalidUserInputException
                        .builder()
                        .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND,
                                String.valueOf(story.getAssignedDeveloperId()))
                        ).build();
            }
            storyEntity.setAssignedDeveloper(developerEntity.get());
        }
        return modelMapper.map(storyRepository.save(storyEntity), StoryDto.class);
    }

    @Override
    public List<StoryDto> getAllStories() {
        return null;
    }

    @Override
    public StoryDto getStoryById(long id) {
        return null;
    }

    @Override
    public StoryDto updateStoryById(long id, StoryRequestModel story) {
        return null;
    }

    @Override
    public void deleteStoryById(long id) {

    }
}
