package sparta.bus10.service.auth;

import sparta.bus10.dto.SigninRequest;
import sparta.bus10.dto.SignupRequest;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    void signUp(SignupRequest signupRequest);

    void signIn(SigninRequest signinRequest, HttpServletResponse response);
}
