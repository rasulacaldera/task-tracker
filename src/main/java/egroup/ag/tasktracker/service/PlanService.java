package egroup.ag.tasktracker.service;

import egroup.ag.tasktracker.dto.WeeklyPlanDto;

import java.util.List;

public interface PlanService {

    List<WeeklyPlanDto> getPlan();
}
