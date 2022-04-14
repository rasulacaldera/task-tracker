package egroup.ag.tasktracker.controller;

import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.dto.DeveloperRequestModel;
import egroup.ag.tasktracker.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public ResponseEntity<DeveloperDto> createDeveloper(@Valid @RequestBody DeveloperRequestModel developer) {

        DeveloperDto responseDeveloper = developerService.createDeveloper(developer);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeveloper);
    }

    @GetMapping
    public ResponseEntity<List<DeveloperDto>> getAllDevelopers() {
        List<DeveloperDto> developers = developerService.getAllDevelopers();
        if (developers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(developers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDto> getDeveloperById(@PathVariable Long id) {

        DeveloperDto responseDeveloper = developerService.getDeveloperById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeveloper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDto> updateDeveloper(@PathVariable(required = true) Long id,
                                                        @Valid @RequestBody DeveloperRequestModel developer) {
        DeveloperDto responseDeveloper = developerService.updateDeveloperById(id, developer);
        return ResponseEntity.status(HttpStatus.OK).body(responseDeveloper);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDeveloperById(@PathVariable Long id) {
        developerService.deleteDeveloperById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
