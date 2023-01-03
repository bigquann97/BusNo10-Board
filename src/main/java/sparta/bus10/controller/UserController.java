package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.user.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Validated SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public void signin(@RequestBody SigninRequestDto signinRequestDto, HttpServletResponse response) {
        userService.signin(signinRequestDto, response);
    }

    @DeleteMapping("/secession")
    public void secession(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.secession(userDetails.getUser());
    }

}