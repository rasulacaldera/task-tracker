package egroup.ag.tasktracker.repository;

import egroup.ag.tasktracker.entity.DeveloperEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<DeveloperEntity, Long> {
    
}
