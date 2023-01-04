package sparta.bus10.exception;

public class LikeException extends RuntimeException{
    public static class NoLikeFoundException extends LikeException {}
    public static class AlreadyLikedException extends LikeException {}
}
