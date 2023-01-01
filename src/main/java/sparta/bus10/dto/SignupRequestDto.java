package sparta.bus10.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {
    @Pattern(regexp = "[a-z0-9]{4,10}", message = "사용자 이름은 알파벳 소문자, 숫자로 구성한 4~10자 사이로 입력해주세요.")
    private String username;

    @Pattern(regexp = "[a-zA-Z0-9]{8,15}", message = "비밀번호는 알파벳 대소문자, 숫자로 구성한 8~15자 사이로 입력해주세요.")
    private String password;
}

