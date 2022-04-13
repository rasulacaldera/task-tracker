package egroup.ag.tasktracker.dto;

import egroup.ag.tasktracker.constants.StoryStatus;
import lombok.Data;

@Data
public class StoryDto extends IssueDto {

    private Long estimation;
    private StoryStatus status;
}
