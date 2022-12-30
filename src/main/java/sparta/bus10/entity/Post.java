package sparta.bus10.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Post extends Timestamp {
    @Id
    @GeneratedValue
    private Long postId;

    @Column(nullable = false)
    private String postTitle;
    @Column(nullable = false)
    private String postContent;
}
