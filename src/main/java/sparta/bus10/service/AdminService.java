package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
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


    // 어드민 권한 게시글 수정
    @Transactional
    public void editPostByAdmin(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        post.changePost(postRequestDto.getUserName(), postRequestDto.getPostTitle(), postRequestDto.getPostContent());
        postRepository.save(post);
    }

    // 어드민 권한 게시글 삭제
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));
        postRepository.delete(post);
    }

    // 어드민 권한 게시글 수정
    @Transactional
    public void editCommentByAdmin(Long postId, Long commentId, CommentRequestDto commentRequestDto){
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        comment.changeComment(commentRequestDto.getCommentContent());
        commentRepository.save(comment);
    }

    // 어드민 권한 댓글 삭제
    @Transactional
    public void deleteCommentByAdmin(Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        commentRepository.delete(comment);
    }

}
