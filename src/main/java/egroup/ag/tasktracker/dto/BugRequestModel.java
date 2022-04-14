package egroup.ag.tasktracker.dto;

import egroup.ag.tasktracker.constants.BugPriority;
import egroup.ag.tasktracker.constants.BugStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BugRequestModel {

    private String title;
    private String description;
    private Long assignedDeveloperId;
    private Date createdDate;
    private Date modifiedDate;
    private Long estimation;
    private BugPriority priority;
    private BugStatus status;
}
