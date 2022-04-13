package egroup.ag.tasktracker.dto;

import egroup.ag.tasktracker.constants.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiError {

    private final String code;
    private final String message;

    public ApiError(ErrorMessage errorMessage, String... args) {
        this.code = errorMessage.getCode();
        this.message = String.format(errorMessage.getMessage(), args);
    }

}
