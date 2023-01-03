package sparta.bus10.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.SigninRequestDto;
import sparta.bus10.dto.SignupRequestDto;
import sparta.bus10.entity.User;
import sparta.bus10.entity.UserRoleEnum;
import sparta.bus10.jwt.JwtUtil;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;
import sparta.bus10.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
    }

    @Override
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

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

    @Override
    @Transactional
    public void secession(User user) {
        likeRepository.deleteByUser(user);
        commentRepository.deleteByUser(user);
        postRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}