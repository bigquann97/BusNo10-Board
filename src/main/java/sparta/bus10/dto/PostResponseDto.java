package sparta.bus10.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Post;

@Getter
@NoArgsConstructor
public class PostResponseDto {
        private String postTitle;
        private String postContent;

        public PostResponseDto(Post post) {
                this.postTitle = post.getPostTitle();
                this.postContent = post.getPostContent();
        }


}
