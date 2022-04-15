import egroup.ag.tasktracker.constants.ErrorMessage
import egroup.ag.tasktracker.dto.DeveloperDto
import egroup.ag.tasktracker.dto.StoryDto
import egroup.ag.tasktracker.dto.WeeklyPlanDto
import egroup.ag.tasktracker.exception.CustomServiceException
import egroup.ag.tasktracker.service.DeveloperService
import egroup.ag.tasktracker.service.PlanService
import egroup.ag.tasktracker.service.StoryService
import egroup.ag.tasktracker.service.impl.PlanServiceImpl
import spock.lang.Shared
import spock.lang.Specification

class PlanServiceTests extends Specification {

    @Shared
    private PlanService planService
    private DeveloperService developerService = Mock(DeveloperService)
    private StoryService storyService = Mock(StoryService)

    private static final long DEVELOPER_CAPACITY_PER_WEEK = 10l;


    def setup() {
        planService = new PlanServiceImpl(storyService, developerService)
    }

    def "An error is thrown when no developers are found"() {
        given:
        developerService.getAllDevelopers() >> []
        when:
        planService.getPlan()
        then:
        Exception ex = thrown(CustomServiceException.class)
        ex.error.code == ErrorMessage.NO_DEVELOPERS_TO_PLAN.code
    }

    def "An error is thrown when no stories are found"() {
        given:
        developerService.getAllDevelopers() >> getDummyDeveloperList(1)
        storyService.getAllStories() >> []
        when:
        planService.getPlan()
        then:
        Exception ex = thrown(CustomServiceException.class)
        ex.error.code == ErrorMessage.NO_STORIES_TO_ESTIMATE.code
    }

    def "Returns a plan | One developer | One valid Story"() {
        given:
        long developerCount = 1
        developerService.getAllDevelopers() >> getDummyDeveloperList(developerCount)
        storyService.getAllStories() >> getDummyStoryList([10])
        when:
        List<WeeklyPlanDto> plan = planService.getPlan()
        then:
        noExceptionThrown()
        plan.size() == 1
        planDoesNotExceedCapacity(developerCount, plan)
    }

    def "Returns a plan | One developer | Three valid Story"() {
        given:
        long developerCount = 1
        developerService.getAllDevelopers() >> getDummyDeveloperList(developerCount)
        storyService.getAllStories() >> getDummyStoryList([7, 10, 3])
        when:
        List<WeeklyPlanDto> plan = planService.getPlan()
        then:
        noExceptionThrown()
        plan.size() == 2
        planDoesNotExceedCapacity(developerCount, plan)
    }

    def "Returns a plan | Two developer | Three valid Story"() {
        given:
        long developerCount = 2
        developerService.getAllDevelopers() >> getDummyDeveloperList(developerCount)
        storyService.getAllStories() >> getDummyStoryList([7, 10, 3])
        when:
        List<WeeklyPlanDto> plan = planService.getPlan()
        then:
        noExceptionThrown()
        plan.size() == 1
        planDoesNotExceedCapacity(developerCount, plan)
    }

    def "Returns a plan | One developer | Five valid Story"() {
        given:
        long developerCount = 1
        developerService.getAllDevelopers() >> getDummyDeveloperList(developerCount)
        storyService.getAllStories() >> getDummyStoryList([7, 10, 3, 3, 3, 3, 7, 8])
        when:
        List<WeeklyPlanDto> plan = planService.getPlan()
        then:
        noExceptionThrown()
        plan.size() == 5
        planDoesNotExceedCapacity(developerCount, plan)
    }

    def "Returns a plan | Two developer | Five valid Story"() {
        given:
        long developerCount = 2
        developerService.getAllDevelopers() >> getDummyDeveloperList(developerCount)
        storyService.getAllStories() >> getDummyStoryList([7, 10, 3, 3, 3, 3, 7, 8])
        when:
        List<WeeklyPlanDto> plan = planService.getPlan()
        then:
        noExceptionThrown()
        plan.size() == 3
        planDoesNotExceedCapacity(developerCount, plan)
    }

    private static List<DeveloperDto> getDummyDeveloperList(count) {

        List<DeveloperDto> developerList = [];

        for (int i = 0; i < count; i++) {
            developerList.add(new DeveloperDto(
                    "id": i,
                    "name": "Dummy Developer"
            ))
        }
        return developerList
    }


    private static List<StoryDto> getDummyStoryList(List<Long> estimates) {

        List<StoryDto> storyList = [];

        storyList.add(new StoryDto(
                "id": 99,
                "title": "Dummy Story"
        ))

        for (int i = 0; i < estimates.size(); i++) {
            storyList.add(new StoryDto(
                    "id": i,
                    "title": "Dummy Story",
                    "estimation": estimates[i]
            ))
        }
        return storyList
    }

    private static boolean planDoesNotExceedCapacity(long developerCount, List<WeeklyPlanDto> plan) {
        long totalCapacityPerWeek = developerCount * DEVELOPER_CAPACITY_PER_WEEK;

        plan.every { weekPlan ->
            return weekPlan.totalStoryPoints <= totalCapacityPerWeek
        }
    }
}