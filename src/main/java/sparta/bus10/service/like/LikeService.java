package sparta.bus10.service.like;

import sparta.bus10.entity.User;

public interface LikeService {
    void likePost(Long postId, User user);

    void unlikePost(Long postId, User user);

    void likeComment(Long commentId, User user);

    void unlikeComment(Long commentId, User user);
}
