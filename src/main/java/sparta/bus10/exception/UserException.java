package sparta.bus10.exception;

public class UserException extends RuntimeException{

    public static class AuthorityException extends UserException {}

    public static class PasswordNotMatchException extends UserException {}

    public static class NoUserFoundException extends UserException {}

    public static class AlreadyExistUserException extends UserException {}

}
