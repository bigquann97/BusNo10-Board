package sparta.bus10.exception.api;

import lombok.Getter;

@Getter
public enum Status {

    F_ILLEGAL_ARGUMENT(400, "잘못된 입력입니다."),
    F_INVALID_TOKEN(401, "잘못된 토큰입니다.")
    ;

    Status (int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;
}
