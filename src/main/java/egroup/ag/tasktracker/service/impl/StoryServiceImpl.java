package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.constants.StoryStatus;
import egroup.ag.tasktracker.dto.ApiError;
import egroup.ag.tasktracker.dto.StoryDto;
import egroup.ag.tasktracker.dto.StoryRequestModel;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.entity.StoryEntity;
import egroup.ag.tasktracker.exception.CustomServiceException;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.repository.StoryRepository;
import egroup.ag.tasktracker.service.StoryService;
import egroup.ag.tasktracker.utils.BeanUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
                throw CustomServiceException
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
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Type listType = new TypeToken<List<StoryDto>>() {
        }.getType();
        return modelMapper.map(storyRepository.findAll(), listType);
    }

    @Override
    public StoryDto getStoryById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        StoryDto bug = modelMapper.map(storyRepository.findById(id), StoryDto.class);
        if (bug == null) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.STORY_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        return bug;
    }

    @Override
    public StoryDto updateStoryById(long id, StoryRequestModel story) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Optional<StoryEntity> storyEntity = storyRepository.findById(id);

        if (storyEntity.isEmpty()) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.STORY_NOT_FOUND, String.valueOf(id))
                    ).build();
        }

        if (story.getAssignedDeveloperId() != null) {
            Optional<DeveloperEntity> developerDto = developerRepository.findById(story.getAssignedDeveloperId());
            if (developerDto.isEmpty()) {
                throw CustomServiceException
                        .builder()
                        .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND,
                                String.valueOf(story.getAssignedDeveloperId()))
                        ).build();
            }
            storyEntity.get().setAssignedDeveloper(developerDto.get());
        }

        BeanUtil.copyIgnoringNullProperties(story, storyEntity.get());
        return modelMapper.map(storyRepository.save(storyEntity.get()), StoryDto.class);
    }

    @Override
    public void deleteStoryById(long id) {
        Optional<StoryEntity> storyEntity = storyRepository.findById(id);
        if (storyEntity.isEmpty()) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.STORY_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        storyRepository.deleteById(id);
    }
}
