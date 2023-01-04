package sparta.bus10.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentRequest;
import sparta.bus10.dto.PostRequest;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.exception.CommentException.CommentNotFoundException;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.List;

import static sparta.bus10.exception.PostException.PostNotFoundException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void editPostByAdmin(Long postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.changePost(post.getUser(), postRequest.getPostTitle(), postRequest.getPostContent());
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        likeRepository.deleteByPost(post);
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

    @Override
    @Transactional
    public void editCommentByAdmin(Long commentId, CommentRequest commentRequest){
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.changeComment(commentRequest.getCommentContent());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentByAdmin(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        List<Comment> replies = commentRepository.findByParentCommentId(comment.getId());
        for (Comment reply : replies) {
            likeRepository.deleteByComment(reply);
        }
        commentRepository.deleteAll(replies);
        commentRepository.delete(comment);
    }

}
