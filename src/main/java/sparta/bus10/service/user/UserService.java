package sparta.bus10.service.user;

import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    void signup(SignupRequestDto signupRequestDto);

    void signin(SigninRequestDto signinRequestDto, HttpServletResponse response);

    void secession(User user);
}
