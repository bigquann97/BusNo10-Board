package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.bus10.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long id);
}
