package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String userName;
    private String commentContent;

    public CommentResponseDto(Comment comment) {
        this.userName = comment.getUsername();
        this.commentContent = comment.getCommentContent();
    }
    public static List<CommentResponseDto> of(List<Comment> comments) {
            List<CommentResponseDto> commentResponseDto = comments.stream().map(x->new CommentResponseDto()).collect(Collectors.toList());
            return commentResponseDto;

    }
    //코멘트 생성시 같은아이디로 2개의 댓글 생성불가

}
