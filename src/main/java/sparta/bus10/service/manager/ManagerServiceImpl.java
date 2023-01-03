package sparta.bus10.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.UserDto;
import sparta.bus10.entity.Apply;
import sparta.bus10.entity.User;
import sparta.bus10.entity.UserRoleEnum;
import sparta.bus10.repository.ApplyRepository;
import sparta.bus10.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Apply> getApplyList() {
        return applyRepository.findAll();
    }

    @Override
    public void applyManager(User user) {
        Optional<Apply> apply = applyRepository.findByUser(user);
        if(apply.isPresent()){
            throw new IllegalArgumentException("이미 진급 신청을 했습니다.");
        }
        applyRepository.save(new Apply(user));
    }

    @Override
    public void acceptApply(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Optional<Apply> apply = applyRepository.findByUser(user);
        if(!apply.isPresent()){
            throw new IllegalArgumentException("등급 신청을 찾을 수 없습니다.");
        }
        user.changeRole(UserRoleEnum.MANAGER);
        applyRepository.delete(apply.get());
    }

    @Override
    public void declineApply(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Optional<Apply> apply = applyRepository.findByUser(user);
        if(!apply.isPresent()){
            throw new IllegalArgumentException("등급 신청을 찾을 수 없습니다.");
        }
        applyRepository.delete(apply.get());
    }


}
