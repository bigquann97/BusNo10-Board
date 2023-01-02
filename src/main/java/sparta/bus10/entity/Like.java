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

    // Like : Post id를 가지고 있어야 함.
    // Like : User id를 가지고 있어야 함.
    // User : Like 리스트를 불러와야 함.
    // Post : like 개수를 불러와야 함.
    @Column(nullable = false)
    private Long postId;
    @Column(nullable = false)
    private Long userId;


    public Like(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
