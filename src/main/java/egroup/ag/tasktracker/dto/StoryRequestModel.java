package egroup.ag.tasktracker.dto;

import egroup.ag.tasktracker.constants.StoryStatus;
import lombok.Data;

import java.util.Date;

@Data
public class StoryRequestModel {

    private String title;
    private String description;
    private Long assignedDeveloperId;
    private Date createdDate;
    private Date modifiedDate;
    private Long estimation;
    private StoryStatus status;
}
