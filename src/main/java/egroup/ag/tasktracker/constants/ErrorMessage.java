package egroup.ag.tasktracker.constants;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    UNHANDLED_EXCEPTION("TATR_0001", "Unhandled Exception"),
    DEVELOPER_NOT_FOUND("TATR_0002", "Cannot find Developer with ID: %s"),
    STORY_NOT_FOUND("TATR_0003", "Cannot find Story with ID: %s"),
    BUG_NOT_FOUND("TATR_0004", "Cannot find Bug with ID: %s"),
    NO_STORIES_TO_ESTIMATE("TATR_0005", "No stories found to be estimated with current capacity : %s"),
    DEVELOPER_IN_USE("TATR_0006", "Unable to delete developer since its currently in use"),
    DATA_VALIDATION_ERROR("TATR_0007", "%s");

    private final String code;
    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
