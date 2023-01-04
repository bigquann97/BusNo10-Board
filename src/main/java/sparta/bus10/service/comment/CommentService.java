package sparta.bus10.service.comment;

import sparta.bus10.dto.CommentRequest;
import sparta.bus10.entity.User;

public interface CommentService {
    void createCommentService(Long postId, CommentRequest commentrequest, User user);

    void editComment(Long commentId, CommentRequest commentRequest, User user);

    void deleteComment(Long commentId, User user);

    void createReply(Long commentId, User user, CommentRequest request);
}
