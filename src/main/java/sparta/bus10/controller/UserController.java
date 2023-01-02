package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody @Validated SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
    }

    @PostMapping("api/auth/signin")
    public void signin(@RequestBody SigninRequestDto signinRequestDto, HttpServletResponse response) {
        userService.signin(signinRequestDto, response);
    }

}