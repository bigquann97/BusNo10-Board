package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentIdAndUsername(@NonNull Long commentId, @NonNull String username);
    List<Comment> findByPostId(@NonNull Long postId);
}
