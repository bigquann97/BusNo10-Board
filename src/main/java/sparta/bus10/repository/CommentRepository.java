package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.bus10.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);


}
