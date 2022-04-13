package egroup.ag.tasktracker.repository;

import egroup.ag.tasktracker.entity.BugEntity;
import org.springframework.data.repository.CrudRepository;

public interface BugRepository extends CrudRepository<BugEntity, Long> {
}
