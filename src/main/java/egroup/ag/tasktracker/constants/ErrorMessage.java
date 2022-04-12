package egroup.ag.tasktracker.constants;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    UNHANDLED_EXCEPTION("TATR_0001", "Unhandled Exception");

    private final String code;
    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
