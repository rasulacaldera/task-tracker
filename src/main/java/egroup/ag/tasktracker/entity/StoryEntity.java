package egroup.ag.tasktracker.entity;

import egroup.ag.tasktracker.constants.StoryStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "story")
@Data
public class StoryEntity extends IssueEntity {

    private Long estimation;

    private StoryStatus status;
}
