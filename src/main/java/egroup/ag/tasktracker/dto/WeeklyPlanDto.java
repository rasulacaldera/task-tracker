package egroup.ag.tasktracker.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeeklyPlanDto {

    private Long totalStoryPoints;
    private List<StoryDto> stories;

    public WeeklyPlanDto() {
        this.stories = new ArrayList<>();
    }
}
