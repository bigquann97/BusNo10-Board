package sparta.bus10.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.bus10.entity.Apply;
import sparta.bus10.entity.User;
import sparta.bus10.repository.ApplyRepository;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ApplyRepository applyRepository;
    @Override
    public void upgradeUser(User user) {
        Apply apply = new Apply(user);
        applyRepository.save(apply);
    }
}
