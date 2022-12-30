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

import java.util.Optional;

// 주방장 - 재료를 꺼내올 냉장고
// Client => Controller => Service => Repository => Service => Controller => Client

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

        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
        // 냉장고 안에 내용물이 변화가 생겨요
    }

    @Transactional(readOnly = true)
    public void signin(SigninRequestDto signinRequestDto){
        String username = signinRequestDto.getUsername();
        String password = signinRequestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if(! passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 이제 정상 로그인 하면 되겠죠?
        // 로그인 로직만 추가를하면은 이제 되겠죠 ?
    }
}

// ADMIN_TOKEN
//    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
//회원 중복 확인

// User가 내려와야하는데, 만약에 그런 User가 없어요 - 그러면 null로 들어와요. 없으니까
// 근데, Optional로 User객체를 한번 감싸서 뱉어주면은, null에 대한 처리가 가능해져요.

//        if (signupRequestDto.isAdmin()) {
//            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }