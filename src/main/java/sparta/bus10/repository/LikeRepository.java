package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    long deleteByPost(@NonNull Post post);

    long deleteByComment(@NonNull Comment comment);

    List<Like> findByUserAndCommentNull(@NonNull User user);

    List<Like> findByUserAndCommentNotNull(@NonNull User user);

    Optional<Like> findByPostAndUser(Post post, User user);

    Optional<Like> findByCommentAndUser(Comment comment, User user);

    int countByPostAndComment(Post post, Comment comment);

}
