package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.service.UserService;

// 점원 역할 - 맞이해주는 역할
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // Controller가 UserService를 필요로하니까 내가만든 UserService 를 주입해줘야겠다

    // POST - http://localhost:8080/api/auth/signup
    // { JSON                      =>    SignupRequestDto
    //   "username": "sparta",
    //   "password": "sparta"
    // }
    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody @Validated SignupRequestDto signupRequestDto) {
        // 지지고 볶고가 필요해요
        // => 유저 회원가입 로직이 필요하다는 말
        userService.signup(signupRequestDto);
    }

    // POST - http://localhost:8080/api/auth/signin
    @PostMapping("api/auth/signin")
    public void signin(@RequestBody SigninRequestDto signinRequestDto){
        userService.signin(signinRequestDto);
        // response 없음 -> void -> return값 없게 수정
    }



}

// @RestController = @Controller + @ResponseBody
// 스프링 내부엔, 객체 => JSON , JSON => 객체 바꿔주는 기계가 안에 들어있어요

// dto = Data Transfer Object