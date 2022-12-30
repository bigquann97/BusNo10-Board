package sparta.bus10.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue
    private Long postId;
}
