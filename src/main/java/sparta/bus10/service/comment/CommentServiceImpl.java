package sparta.bus10.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentRequest;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
import sparta.bus10.exception.CommentException.CommentNotFoundException;
import sparta.bus10.exception.PostException.PostNotFoundException;
import sparta.bus10.exception.UserException;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void createCommentService(Long postId, CommentRequest commentrequest, User user) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = new Comment(post, user, commentrequest.getCommentContent());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void editComment(Long commentId, CommentRequest commentRequest, User user) {
        Comment comment = commentRepository.findByIdAndUser(commentId, user).orElseThrow(UserException.AuthorityException::new);
        comment.changeComment(commentRequest.getCommentContent());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findByIdAndUser(commentId, user).orElseThrow(UserException.AuthorityException::new);
        List<Comment> replies = commentRepository.findByParentCommentId(comment.getId());
        for (Comment reply : replies) {
            likeRepository.deleteByComment(reply);
        }
        commentRepository.deleteAll(replies);
        commentRepository.delete(comment);
        likeRepository.deleteByComment(comment);
    }

    @Override
    @Transactional
    public void createReply(Long commentId, User user, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Comment reply = new Comment(comment.getPost(), user, request.getCommentContent(), true, comment.getId());
        commentRepository.save(reply);
    }
}
