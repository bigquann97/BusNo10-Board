package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.dto.CommentResponseDto;

import javax.persistence.*;
import java.security.PrivateKey;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false)
    private Long postId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String commentContent;
    public Comment(Long postId, String requestUsername,String requestCommentContent){
        this.postId = postId;
        this.username = requestUsername;
        this.commentContent = requestCommentContent;
    }
    public void changeComment(String requestComment){
        this.commentContent = requestComment;
    }

}