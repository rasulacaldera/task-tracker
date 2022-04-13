package egroup.ag.tasktracker.entity;

import egroup.ag.tasktracker.constants.BugPriority;
import egroup.ag.tasktracker.constants.BugStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bug")
@Data
public class BugEntity extends IssueEntity {

    private BugPriority priority;

    private BugStatus status;
}
