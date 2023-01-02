package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.entity.User;
import sparta.bus10.entity.UserRoleEnum;
import sparta.bus10.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserRoleEnum role = UserRoleEnum.ROLE_USER;
        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void signin(SigninRequestDto signinRequestDto, HttpServletResponse response){
        String username = signinRequestDto.getUsername();
        String password = signinRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //로그인 로직
        response.addHeader("Authorization", "Bearer otn239023"); // JWT 유효한 토큰값을 하나 주는게
    }

}