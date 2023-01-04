package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponse {

    private Long postId;
    private String userName;
    private String postTitle;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int postLikeCount = 0;
    private List<CommentResponse> commentList = new ArrayList<>();

    public PostResponse(Post post, List<Comment> comments, int postLikeCount) {
        Map<Long, CommentResponse> tempList = new HashMap<>();
        for (Comment comment : comments) {
            if(comment.isReply()) {
                Long parentId = comment.getParentCommentId();
                CommentResponse target = tempList.get(parentId);
                CommentResponse reply = new CommentResponse(comment);
                target.addReply(reply);
                continue;
            }
            Long id = comment.getId();

            CommentResponse response = new CommentResponse(comment);
            tempList.put(id, response);
        }
        this.commentList = tempList.values().stream().sorted(Comparator.comparing(CommentResponse::getCreatedAt)).collect(Collectors.toList());
        this.postId = post.getId();
        this.userName = post.getUser().getUsername();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.postLikeCount = postLikeCount;
    }
}
