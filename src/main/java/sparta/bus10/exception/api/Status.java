package sparta.bus10.exception.api;

import lombok.Getter;

@Getter
public enum Status {

    APPLY(400, "신청 오류"),
    APPLY_NOT_APPLIED(400, "진급 신청한 이력이 없습니다."),
    APPLY_ALREADY_APPLIED(400, "이미 진급 신청했습니다."),

    COMMENT(400, "댓글 오류"),
    COMMENT_NOT_FOUND(400, "존재하지 않는 댓글입니다."),

    LIKE(400, "좋아요 오류"),
    LIKE_NOT_FOUND(400, "좋아요 이력이 없습니다."),
    LIKE_ALREADY_LIKED(400, "이미 좋아요가 존재합니다."),

    POST(400, "게시글 오류"),
    POST_NOT_FOUND(400, "존재하지 않는 게시물입니다."),

    TOKEN(400, "토큰 오류"),

    USER(400, "유저 오류"),
    USER_AUTHORITY(400, "접근 권한이 없습니다."),
    USER_PASSWORD_NOT_MATCHES(400, "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(400, "존재하지 않는 유저입니다."),
    USER_ALREADY_EXISTS(400, "이미 존재하는 회원 아이디입니다."),


    ILLEGAL_ARGUMENT(400, "잘못된 입력입니다."),
    INVALID_TOKEN(401, "잘못된 토큰입니다.")
    ;

    Status (int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;
}
