package egroup.ag.tasktracker.controller;

import egroup.ag.tasktracker.dto.WeeklyPlanDto;
import egroup.ag.tasktracker.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<List<WeeklyPlanDto>> getPlan() {

        List<WeeklyPlanDto> stories = planService.getPlan();
        return ResponseEntity.status(HttpStatus.OK).body(stories);
    }
}
