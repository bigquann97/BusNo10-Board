package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private boolean isReply;

    private Long parentCommentId;

    public Comment(Long postId, String requestUsername,String requestCommentContent){
        this.postId = postId;
        this.username = requestUsername;
        this.commentContent = requestCommentContent;
        this.isReply = false;
        this.parentCommentId = null;
    }

    public Comment(Long postId, String requestUsername, String requestCommentContent, boolean isReply, Long parentCommentId){
        this.postId = postId;
        this.username = requestUsername;
        this.commentContent = requestCommentContent;
        this.isReply = isReply;
        this.parentCommentId = parentCommentId;
    }

    public void changeComment(String requestComment){
        this.commentContent = requestComment;
    }

}