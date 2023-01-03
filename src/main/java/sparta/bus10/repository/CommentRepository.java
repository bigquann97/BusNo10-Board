package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long deleteByUser(@NonNull User user);
    List<Comment> findByParentCommentId(@NonNull Long parentCommentId);
    long deleteByPost(@NonNull Post post);
    List<Comment> findByUser(@NonNull User user);
    Optional<Comment> findByIdAndUser(@NonNull Long id, @NonNull User user);
    List<Comment> findByPost(@NonNull Post post);
}
