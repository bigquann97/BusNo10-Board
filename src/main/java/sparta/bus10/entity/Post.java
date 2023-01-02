package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.dto.CommentResponseDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    public Post(String username, String requestPostTitle, String requestPostContent) {
        this.username = username;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

    public void changePost(String username, String requestPostTitle, String requestPostContent) {
        this.username = username;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

}
