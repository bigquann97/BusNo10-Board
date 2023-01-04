package sparta.bus10.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.entity.Apply;
import sparta.bus10.entity.User;
import sparta.bus10.entity.UserRoleEnum;
import sparta.bus10.exception.ApplyException;
import sparta.bus10.exception.ApplyException.AlreadyAppliedException;
import sparta.bus10.exception.ApplyException.NotAppliedException;
import sparta.bus10.exception.UserException;
import sparta.bus10.repository.ApplyRepository;
import sparta.bus10.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagementServiceImpl implements ManagementService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Apply> getApplyList() {
        return applyRepository.findAll();
    }

    @Override
    public void applyManager(User user) {
        validateIfUserAlreadyApplied(user);
        applyRepository.save(new Apply(user));
    }

    @Override
    public void acceptApply(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Apply apply = applyRepository.findByUser(user).orElseThrow(NotAppliedException::new);
        user.changeRole(UserRoleEnum.MANAGER);
        applyRepository.delete(apply);
    }

    @Override
    public void declineApply(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserException::new);
        Apply apply = applyRepository.findByUser(user).orElseThrow(ApplyException::new);
        applyRepository.delete(apply);
    }

    private void validateIfUserAlreadyApplied(User user) {
        if(applyRepository.existsByUser(user))
            throw new AlreadyAppliedException();
    }
}
