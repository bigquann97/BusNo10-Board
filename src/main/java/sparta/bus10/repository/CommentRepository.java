package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(@NonNull Long id, @NonNull User user);

    List<Comment> findByParentCommentId(@NonNull Long parentCommentId);

    List<Comment> findByUser(@NonNull User user);

    List<Comment> findByPost(@NonNull Post post);

    void deleteByPost(@NonNull Post post);
}
