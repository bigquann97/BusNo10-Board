package sparta.bus10.exception;

public class PostException extends RuntimeException {
    public static class PostNotFoundException extends PostException {}
}
