package sparta.bus10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.bus10.entity.Post;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String userName;
    private String postTitle;
    private String postContent;
}
