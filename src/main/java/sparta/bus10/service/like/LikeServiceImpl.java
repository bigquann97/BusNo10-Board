package sparta.bus10.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
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

    @Transactional
    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물을 찾을 수 없습니다.")
        );
        Optional<Like> found = likeRepository.findByPostAndUser(post, user);

        if(found.isPresent()){
            throw new IllegalArgumentException("이미 좋아요 한 게시물입니다.");
        }
        Like like = new Like(post,null, user);
        likeRepository.save(like);
    }

    @Transactional
    public void unlikePost(Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물을 찾을 수 없습니다.")
        );
        Optional<Like> like = likeRepository.findByPostAndUser(post, user);
        if(!like.isPresent()){
            throw new IllegalArgumentException("좋아요를 하지 않은 게시물입니다.");
        }
        likeRepository.delete(like.get());
    }

    @Transactional
    public void likeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        Optional<Like> found = likeRepository.findByCommentAndUser(comment, user);
        if(found.isPresent()){
            throw new IllegalArgumentException("이미 좋아요 한 댓글입니다.");
        }
        Like like = new Like(comment.getPost(), comment, user);
        likeRepository.save(like);
    }

    @Transactional
    public void unlikeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        Optional<Like> like = likeRepository.findByCommentAndUser(comment, user);
        if(!like.isPresent()){
            throw new IllegalArgumentException("좋아요를 하지 않은 댓글입니다.");
        }
        likeRepository.delete(like.get());
    }
}
