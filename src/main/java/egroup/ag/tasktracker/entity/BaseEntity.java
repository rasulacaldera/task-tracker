package egroup.ag.tasktracker.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "create_date")
//    private Date createdDate;
//
//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "modify_date")
//    private Date modifiedDate;
}
