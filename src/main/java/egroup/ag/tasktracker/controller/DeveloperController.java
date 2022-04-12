package egroup.ag.tasktracker.controller;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public ResponseEntity<DeveloperDto> createDeveloper(@Valid @RequestBody CreateDeveloperModel developer) {

        DeveloperDto responseDeveloper = developerService.createDeveloper(developer);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeveloper);
    }
}
