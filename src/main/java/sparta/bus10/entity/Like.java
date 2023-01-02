package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "likes")
@Getter
@NoArgsConstructor
public class Like extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long postId;
    @Column
    private Long commentId;
    @Column(nullable = false)
    private Long userId;


    public Like(Long postId, Long commentId, Long userId) {
        this.postId = postId;
        this.commentId = commentId;
        this.userId = userId;
    }

}
