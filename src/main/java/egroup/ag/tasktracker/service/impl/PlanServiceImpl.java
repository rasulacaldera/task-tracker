package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.dto.ApiError;
import egroup.ag.tasktracker.dto.StoryDto;
import egroup.ag.tasktracker.dto.WeeklyPlanDto;
import egroup.ag.tasktracker.exception.CustomServiceException;
import egroup.ag.tasktracker.service.DeveloperService;
import egroup.ag.tasktracker.service.PlanService;
import egroup.ag.tasktracker.service.StoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    private final long weeklyCapacityPerDeveloper = 10;
    private final StoryService storyService;
    private final DeveloperService developerService;

    public PlanServiceImpl(StoryService storyService, DeveloperService developerService) {
        this.storyService = storyService;
        this.developerService = developerService;
    }

    @Override
    public List<WeeklyPlanDto> getPlan() {

        long weeklyCapacity = weeklyCapacityPerDeveloper * developerService.getAllDevelopers().size();

        List<StoryDto> estimatedStories = storyService.getAllStories()
                .stream()
                .filter(story -> story.getEstimation() != null && story.getEstimation() <= weeklyCapacity)
                .sorted(Comparator.comparingLong(StoryDto::getEstimation).reversed())
                .collect(Collectors.toList());

        if (estimatedStories.isEmpty()) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.NO_STORIES_TO_ESTIMATE,
                            String.valueOf(weeklyCapacity))
                    ).build();
        }

        List<WeeklyPlanDto> finalPlan = new ArrayList<>();
        WeeklyPlanDto weeklyPlan = new WeeklyPlanDto();
        getWeekPlan(estimatedStories, weeklyCapacity, finalPlan, weeklyPlan);
        return finalPlan;
    }

    private void getWeekPlan(List<StoryDto> estimatedStories, long weeklyCapacity,
                             List<WeeklyPlanDto> finalPlan, WeeklyPlanDto weeklyPlan) {

        long remainingCapacity = getRemainingCapacityForWeek(weeklyPlan, weeklyCapacity);

        Optional<StoryDto> pickedStory = estimatedStories
                .stream()
                .filter(story -> story.getEstimation() <= remainingCapacity)
                .findFirst();

        if (pickedStory.isPresent()) {
            weeklyPlan.getStories().add(pickedStory.get());
            estimatedStories.remove(pickedStory.get());
        } else {
            addWeekPlanToFinalPlan(finalPlan, weeklyPlan);
            weeklyPlan = new WeeklyPlanDto();
        }

        if (estimatedStories.isEmpty()) {
            addWeekPlanToFinalPlan(finalPlan, weeklyPlan);
        } else {
            getWeekPlan(estimatedStories, weeklyCapacity, finalPlan, weeklyPlan);
        }
    }

    private void addWeekPlanToFinalPlan(List<WeeklyPlanDto> finalPlan, WeeklyPlanDto weeklyPlan) {
        long totalStoryPointsForWeek = weeklyPlan.getStories()
                .stream()
                .map(StoryDto::getEstimation)
                .mapToLong(Long::longValue).sum();
        weeklyPlan.setTotalStoryPoints(totalStoryPointsForWeek);
        finalPlan.add(weeklyPlan);
    }

    private long getRemainingCapacityForWeek(WeeklyPlanDto weeklyPlan, long weeklyCapacity) {
        return weeklyCapacity - weeklyPlan.getStories()
                .stream()
                .map(StoryDto::getEstimation)
                .mapToLong(Long::longValue).sum();

    }

}
