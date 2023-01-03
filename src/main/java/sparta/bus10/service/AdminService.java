package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void editPostByAdmin(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        post.changePost(post.getUser(), postRequestDto.getPostTitle(), postRequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));
        postRepository.delete(post);
    }

    @Transactional
    public void editCommentByAdmin(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        comment.changeComment(commentRequestDto.getCommentContent());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteCommentByAdmin(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        commentRepository.delete(comment);
    }

}
