package sparta.bus10.service.myInfo;

import sparta.bus10.dto.CommentResponse;
import sparta.bus10.dto.PostResponse;
import sparta.bus10.entity.User;

import java.util.List;

public interface MyService {
    List<PostResponse> getMyPosts(User user);

    List<CommentResponse> getMyComments(User user);

    List<PostResponse> getMyLikedPosts(User user);

    List<CommentResponse> getMyLikedComments(User user);
}