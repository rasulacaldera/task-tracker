package egroup.ag.tasktracker.exception;

import egroup.ag.tasktracker.dto.ApiError;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomServiceException extends RuntimeException {
    private final transient ApiError error;
}
