package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.StoryDto;
import egroup.ag.tasktracker.dto.StoryRequestModel;

import java.util.List;

public interface StoryService {

    StoryDto createStory(StoryRequestModel story);

    List<StoryDto> getAllStories();

    StoryDto getStoryById(long id);

    StoryDto updateStoryById(long id, StoryRequestModel story);

    void deleteStoryById(long id);
}
