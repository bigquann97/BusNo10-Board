package sparta.bus10.exception;

public class ApplyException extends RuntimeException {
    public static class NotAppliedException extends ApplyException {}

    public static class AlreadyAppliedException extends ApplyException {}
}
