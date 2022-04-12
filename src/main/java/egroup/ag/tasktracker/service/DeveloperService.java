package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;

public interface DeveloperService {

    public DeveloperDto createDeveloper(CreateDeveloperModel developer);
}
