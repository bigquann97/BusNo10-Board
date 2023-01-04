package sparta.bus10.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.bus10.exception.*;
import sparta.bus10.exception.api.RestApiException;
import sparta.bus10.exception.api.Status;

import static sparta.bus10.exception.UserException.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ApplyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException applyException(ApplyException e) {
        log.error("e = {}", e.getMessage());

        if (e instanceof ApplyException.AlreadyAppliedException)
            return new RestApiException(Status.APPLY_ALREADY_APPLIED);
        else if (e instanceof ApplyException.NotAppliedException)
            return new RestApiException(Status.APPLY_NOT_APPLIED);

        return new RestApiException(Status.APPLY);
    }

    @ExceptionHandler(CommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException commentException(CommentException e) {
        log.error("e = {}", e.getMessage());

        if (e instanceof CommentException.CommentNotFoundException)
            return new RestApiException(Status.COMMENT_NOT_FOUND);

        return new RestApiException(Status.COMMENT);
    }

    @ExceptionHandler(LikeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException likeException(LikeException e) {
        log.error("e = {}", e.getMessage());

        if(e instanceof LikeException.NoLikeFoundException)
            return new RestApiException(Status.LIKE_NOT_FOUND);
        else if(e instanceof LikeException.AlreadyLikedException)
            return new RestApiException(Status.LIKE_ALREADY_LIKED);

        return new RestApiException(Status.LIKE);
    }

    @ExceptionHandler(PostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException postException(PostException e) {
        log.error("e = {}", e.getMessage());

        if(e instanceof PostException.PostNotFoundException)
            return new RestApiException(Status.POST_NOT_FOUND);

        return new RestApiException(Status.POST);
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException tokenException(TokenException e) {
        log.error("e = {}", e.getMessage());

        return new RestApiException(Status.TOKEN);
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException userException(UserException e) {
        log.error("e = {}", e.getMessage());

        if(e instanceof NoUserFoundException)
            return new RestApiException(Status.USER_NOT_FOUND);
        else if(e instanceof AuthorityException)
            return new RestApiException(Status.USER_AUTHORITY);
        else if(e instanceof AlreadyExistUserException)
            return new RestApiException(Status.USER_ALREADY_EXISTS);
        else if(e instanceof PasswordNotMatchException)
            return new RestApiException(Status.USER_PASSWORD_NOT_MATCHES);

        return new RestApiException(Status.USER);
    }

    // 커스텀 외 익셉션
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException illegalArgumentException(IllegalArgumentException e) {
        log.error("e = {}", e.getMessage());

        return new RestApiException(Status.ILLEGAL_ARGUMENT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException accessDeniedException(AccessDeniedException e) {
        log.error("e = {}", e.getMessage());

        return new RestApiException(Status.USER_AUTHORITY);
    }

}
