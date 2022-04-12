package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;

import java.util.List;

public interface DeveloperService {

    DeveloperDto createDeveloper(CreateDeveloperModel developer);

    List<DeveloperDto> getAllDevelopers();

    DeveloperDto getDeveloperById(long id);

    void deleteDeveloperById(long id);
}
