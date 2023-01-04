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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String commentContent;

    private boolean isReply;

    private Long parentCommentId;

    public Comment(Post post, User user, String requestCommentContent){
        this.post = post;
        this.user = user;
        this.commentContent = requestCommentContent;
        this.isReply = false;
        this.parentCommentId = null;
    }

    public Comment(Post post, User user, String requestCommentContent, boolean isReply, Long parentCommentId){
        this.post = post;
        this.user = user;
        this.commentContent = requestCommentContent;
        this.isReply = isReply;
        this.parentCommentId = parentCommentId;
    }

    public void changeComment(String requestComment){
        this.commentContent = requestComment;
    }

}