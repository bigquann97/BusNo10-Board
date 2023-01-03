package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.LikeRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String userName;
    private String postTitle;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int postLikeCount = 0;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post, List<Comment> comments,int likePost) {
        Map<Long, CommentResponseDto> tempList = new HashMap<>();
        for (Comment comment : comments) {
            if(comment.isReply()) {
                Long parentId = comment.getParentCommentId();
                CommentResponseDto target = tempList.get(parentId);
                CommentResponseDto reply = new CommentResponseDto(comment);
                target.addReply(reply);
                continue;
            }

            Long id = comment.getId();
            CommentResponseDto response = new CommentResponseDto(comment);
            tempList.put(id, response);
//            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
//            commentList.add(commentResponseDto);
        }
        this.commentList = tempList.values().stream().sorted(Comparator.comparing(CommentResponseDto::getCreatedAt)).collect(Collectors.toList());
        this.postId = post.getId();
        this.userName = post.getUser().getUsername();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.postLikeCount = likePost;
    }
}
