package sparta.bus10.service.myInfo;

import sparta.bus10.dto.CommentResponseDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.User;

import java.util.List;

public interface MyService {
    List<PostResponseDto> getMyPosts(User user);

    List<CommentResponseDto> getMyComments(User user);

    List<PostResponseDto> getMyLikedPosts(User user);

    List<CommentResponseDto> getMyLikedComments(User user);
}