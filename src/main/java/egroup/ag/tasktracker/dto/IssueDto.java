package egroup.ag.tasktracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class IssueDto {

    private long id;
    private String title;
    private String description;
    private DeveloperDto assignedDeveloper;
    private Date createdDate;
    private Date modifiedDate;

}
