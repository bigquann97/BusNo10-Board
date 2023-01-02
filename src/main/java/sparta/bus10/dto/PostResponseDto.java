package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String userName;
    private String postTitle;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post, List<Comment> comments) {
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentList.add(commentResponseDto);
        }
        this.postId = post.getPostId();
        this.userName = post.getUsername();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
