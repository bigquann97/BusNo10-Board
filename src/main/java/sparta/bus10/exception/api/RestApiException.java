package sparta.bus10.exception.api;

public class RestApiException {
    private int code;
    private String errorMessage;

    public RestApiException(Status status) {
        this.code = status.getCode();
        this.errorMessage = status.getMsg();
    }
}
