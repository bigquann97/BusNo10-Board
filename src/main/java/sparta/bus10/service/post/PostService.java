package sparta.bus10.service.post;

import sparta.bus10.dto.PostRequest;
import sparta.bus10.dto.PostResponse;
import sparta.bus10.entity.User;

import java.util.List;

public interface PostService {
    void createPost(PostRequest postrequest, User user);

    List<PostResponse> getPostAll();

    PostResponse getPostOne(Long postId);

    void editPost(Long postId, PostRequest postrequest, User user);

    void deletePost(Long postId, User user);
}
