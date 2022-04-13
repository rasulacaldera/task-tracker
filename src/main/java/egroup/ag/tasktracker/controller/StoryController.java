package egroup.ag.tasktracker.controller;

import egroup.ag.tasktracker.dto.StoryDto;
import egroup.ag.tasktracker.dto.StoryRequestModel;
import egroup.ag.tasktracker.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping
    public ResponseEntity<StoryDto> createStory(@Valid @RequestBody StoryRequestModel story) {

        StoryDto responseStory = storyService.createStory(story);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseStory);
    }

    @GetMapping
    public ResponseEntity<List<StoryDto>> getAllStories() {
        List<StoryDto> stories = storyService.getAllStories();
        if (stories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(stories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDto> getStoryById(@PathVariable Long id) {

        StoryDto responseStory = storyService.getStoryById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoryDto> updateStory(@PathVariable(required = true) Long id,
                                                @Valid @RequestBody StoryRequestModel story) {
        StoryDto responseStory = storyService.updateStoryById(id, story);
        return ResponseEntity.status(HttpStatus.OK).body(responseStory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStoryById(@PathVariable Long id) {
        storyService.deleteStoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
