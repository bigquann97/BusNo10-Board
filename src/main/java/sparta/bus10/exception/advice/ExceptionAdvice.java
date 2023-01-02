package sparta.bus10.exception.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.bus10.exception.TokenException;
import sparta.bus10.exception.api.RestApiException;
import sparta.bus10.exception.api.Status;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException illegalArgumentException(IllegalArgumentException e) {
        log.error("e = {}", e.getMessage());
        return new RestApiException(Status.F_ILLEGAL_ARGUMENT);
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException invalidTokenException(TokenException e) {
        log.error("e = {}", e.getMessage());
        return new RestApiException(Status.F_INVALID_TOKEN);
    }

}
