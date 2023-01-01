package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {

        private String userName;
        private String postTitle;
        private String postContent;
        private List<CommentResponseDto> commentList = new ArrayList<>();

        public PostResponseDto(String userName , String postTitle , String postContent, List<Comment> comments){
                for (Comment comment : comments) {
                        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
                        commentList.add(commentResponseDto);
                }
                this.userName = userName;
                this.postTitle = postTitle;
                this.postContent = postContent;
        }

}
