package egroup.ag.tasktracker.repository;

import egroup.ag.tasktracker.entity.StoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface StoryRepository extends CrudRepository<StoryEntity, Long> {
}
