package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> deleteByUser(@NonNull User user);
    List<Post> findPostsByUserOrderByCreatedAt(User user);
}
