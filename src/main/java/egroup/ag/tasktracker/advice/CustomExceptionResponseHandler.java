package egroup.ag.tasktracker.advice;

import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.dto.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionResponseHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionResponseHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        LOG.info("Unexpected error - {}", ex.getMessage());
        return new ResponseEntity<>(ApiError.builder()
                .code(ErrorMessage.UNHANDLED_EXCEPTION.getCode())
                .message(ErrorMessage.UNHANDLED_EXCEPTION.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
