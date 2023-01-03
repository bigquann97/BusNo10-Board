package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Apply;
import sparta.bus10.entity.User;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findByUser(User user);
}
