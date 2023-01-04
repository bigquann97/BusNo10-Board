package sparta.bus10.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.SigninRequest;
import sparta.bus10.dto.SignupRequest;
import sparta.bus10.entity.User;
import sparta.bus10.entity.UserRoleEnum;
import sparta.bus10.exception.UserException.AlreadyExistUserException;
import sparta.bus10.exception.UserException.NoUserFoundException;
import sparta.bus10.jwt.JwtUtil;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;
import sparta.bus10.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;

import static sparta.bus10.exception.UserException.PasswordNotMatchException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void signUp(SignupRequest signupRequest){
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();
        validateUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void signIn(SigninRequest signinRequest, HttpServletResponse response){
        String username = signinRequest.getUsername();
        String rawPassword = signinRequest.getPassword();
        User user = userRepository.findByUsername(username).orElseThrow(NoUserFoundException::new);
        validatePassword(rawPassword, user.getPassword());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

    private void validateUsername(String username) {
        if(userRepository.existsByUsername(username)) {
            throw new AlreadyExistUserException();
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new PasswordNotMatchException();
        }
    }

}