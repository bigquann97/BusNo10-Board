package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.dto.PostRequestDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamp {
    @Id
    @GeneratedValue
    private Long postId;

    @Column(nullable = false)
    private String postTitle;
    @Column(nullable = false)
    private String postContent;

    public Post(PostRequestDto requestDto) {
        this.postTitle = requestDto.getPostTitle();
        this.postContent = requestDto.getPostContent();
    }
}
