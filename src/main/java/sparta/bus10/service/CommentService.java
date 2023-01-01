package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createCommentService(Long postId ,CommentRequestDto commentrequestDto) {
       postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
       );
       var comment = new Comment(postId,commentrequestDto.getUserName(),commentrequestDto.getCommentContent());
        commentRepository.save(comment);
    }
    @Transactional
    public void editComment(Long postId ,Long commentId, CommentRequestDto commentRequestDto) {
        postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        //유효성검사 이름 비교
        if (!comment.getUsername().equals(commentRequestDto.getUserName())){
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        comment.changeComment(commentRequestDto.getCommentContent());
        commentRepository.save(comment);
    }
    @Transactional
    public void deleteComment(Long postId,Long commentId, String userName){
        postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        if (!comment.getUsername().equals(userName)){
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        commentRepository.delete(comment);
    }
}
