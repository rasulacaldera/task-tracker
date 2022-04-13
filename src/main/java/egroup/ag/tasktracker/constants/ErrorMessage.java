package egroup.ag.tasktracker.constants;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    UNHANDLED_EXCEPTION("TATR_0001", "Unhandled Exception"),
    DEVELOPER_NOT_FOUND("TATR_0002", "Cannot find Developer with ID: %s"),
    STORY_NOT_FOUND("TATR_0003", "Cannot find Story with ID: %s");

    private final String code;
    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
