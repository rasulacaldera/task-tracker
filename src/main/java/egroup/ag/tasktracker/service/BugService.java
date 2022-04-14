package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.BugDto;
import egroup.ag.tasktracker.dto.BugRequestModel;

import java.util.List;

public interface BugService {

    BugDto createBug(BugRequestModel bug);

    List<BugDto> getAllBugs();

    BugDto getBugById(long id);

    BugDto updateBugById(long id, BugRequestModel bug);

    void deleteBugById(long id);
}
