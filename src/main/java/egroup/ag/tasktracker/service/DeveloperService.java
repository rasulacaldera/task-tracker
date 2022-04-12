package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;

public interface DeveloperService {

    DeveloperDto createDeveloper(CreateDeveloperModel developer);
}
