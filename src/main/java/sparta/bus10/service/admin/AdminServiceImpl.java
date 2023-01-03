package sparta.bus10.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void editPostByAdmin(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        post.changePost(post.getUser(), postRequestDto.getPostTitle(), postRequestDto.getPostContent());
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));
        likeRepository.deleteByPost(post);
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

    @Override
    @Transactional
    public void editCommentByAdmin(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        comment.changeComment(commentRequestDto.getCommentContent());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentByAdmin(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        List<Comment> replies = commentRepository.findByParentCommentId(comment.getId());
        for (Comment reply : replies) {
            likeRepository.deleteByComment(reply);
        }
        commentRepository.deleteAll(replies);
        commentRepository.delete(comment);
    }

}
