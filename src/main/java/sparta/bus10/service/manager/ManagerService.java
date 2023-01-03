package sparta.bus10.service.manager;
import sparta.bus10.entity.Apply;
import sparta.bus10.entity.User;

import java.util.List;

public interface ManagerService {
    List<Apply> getApplyList();
    void applyManager(User user);

    void acceptApply(Long userId);

    void declineApply(Long userId);
}
