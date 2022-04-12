package egroup.ag.tasktracker.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developers")
@Data
public class DeveloperEntity extends BaseEntity {


    @Column(nullable = false)
    private String name;
}
