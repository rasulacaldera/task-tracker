package egroup.ag.tasktracker.dto;

import egroup.ag.tasktracker.constants.BugPriority;
import egroup.ag.tasktracker.constants.BugStatus;
import lombok.Data;

@Data
public class BugDto extends IssueDto {

    private BugPriority priority;
    private BugStatus status;
}
