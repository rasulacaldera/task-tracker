package egroup.ag.tasktracker.controller;

import egroup.ag.tasktracker.dto.BugDto;
import egroup.ag.tasktracker.dto.BugRequestModel;
import egroup.ag.tasktracker.service.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/bug")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping
    public ResponseEntity<BugDto> createBug(@Valid @RequestBody BugRequestModel bug) {

        BugDto responseBug = bugService.createBug(bug);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBug);
    }

    @GetMapping
    public ResponseEntity<List<BugDto>> getAllBugs() {
        List<BugDto> bugs = bugService.getAllBugs();
        if (bugs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bugs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugDto> getBugById(@PathVariable Long id) {

        BugDto responseBug = bugService.getBugById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBug);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BugDto> updateBug(@PathVariable(required = true) Long id,
                                            @Valid @RequestBody BugRequestModel bug) {
        BugDto responseBug = bugService.updateBugById(id, bug);
        return ResponseEntity.status(HttpStatus.OK).body(responseBug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBugById(@PathVariable Long id) {
        bugService.deleteBugById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
