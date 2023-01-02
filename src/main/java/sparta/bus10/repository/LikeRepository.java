package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.bus10.entity.Like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}
