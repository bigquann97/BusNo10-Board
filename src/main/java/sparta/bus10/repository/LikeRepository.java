package sparta.bus10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    Optional<Like> findByCommentAndUser(Comment comment, User user);
    List<Like> findByUserAndPost(User user, Post post);
    List<Like> findByUserAndComment(User user, Comment comment);
}
