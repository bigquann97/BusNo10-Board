package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String userName;
    private String commentContent;
    private List<CommentResponseDto> replies = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.userName = comment.getUser().getUsername();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public void addReply(CommentResponseDto reply) {
        this.replies.add(reply);
    }
}

//코멘트 생성시 같은아이디로 2개의 댓글 생성불가
