package sparta.bus10.service.post;

import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.User;

import java.util.List;

public interface PostService {
    void createPost(PostRequestDto postrequestDto, User user);

    List<PostResponseDto> getPostAll();

    PostResponseDto getPostOne(Long postId);

    void editPost(Long postId, PostRequestDto postrequestDto, User user);

    void deletePost(Long postId, User user);
}
