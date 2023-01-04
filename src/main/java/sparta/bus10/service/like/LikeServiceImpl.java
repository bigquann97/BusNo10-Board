package sparta.bus10.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
import sparta.bus10.exception.CommentException.CommentNotFoundException;
import sparta.bus10.exception.LikeException.AlreadyLikedException;
import sparta.bus10.exception.LikeException.NoLikeFoundException;
import sparta.bus10.exception.PostException.PostNotFoundException;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Optional<Like> found = likeRepository.findByPostAndUser(post, user);
        validateIfUserAlreadyLiked(found);
        Like like = new Like(post, null, user);
        likeRepository.save(like);
    }

    @Override
    @Transactional
    public void unlikePost(Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Like like = likeRepository.findByPostAndUser(post, user).orElseThrow(NoLikeFoundException::new);
        likeRepository.delete(like);
    }

    @Override
    @Transactional
    public void likeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Optional<Like> found = likeRepository.findByCommentAndUser(comment, user);
        validateIfUserAlreadyLiked(found);
        Like like = new Like(comment.getPost(), comment, user);
        likeRepository.save(like);
    }

    @Override
    @Transactional
    public void unlikeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Like like = likeRepository.findByCommentAndUser(comment, user).orElseThrow(NoLikeFoundException::new);
        likeRepository.delete(like);
    }

    private void validateIfUserAlreadyLiked(Optional<Like> found) {
        if(found.isPresent())
            throw new AlreadyLikedException();
    }
}
