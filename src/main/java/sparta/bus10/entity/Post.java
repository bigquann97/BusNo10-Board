package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    public Post(User user, String requestPostTitle, String requestPostContent) {
        this.user = user;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

    public void changePost(User user, String requestPostTitle, String requestPostContent) {
        this.user = user;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

    public boolean validateUser(User user) {
        return this.user.getId().equals(user.getId());
    }
}
