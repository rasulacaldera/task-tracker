package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.dto.DeveloperRequestModel;

import java.util.List;

public interface DeveloperService {

    DeveloperDto createDeveloper(DeveloperRequestModel developer);

    List<DeveloperDto> getAllDevelopers();

    DeveloperDto getDeveloperById(long id);

    DeveloperDto updateDeveloperById(long id, DeveloperRequestModel developer);

    void deleteDeveloperById(long id);
}
