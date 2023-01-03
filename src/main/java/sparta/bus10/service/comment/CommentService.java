package sparta.bus10.service.comment;

import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.entity.User;

public interface CommentService {
    void createCommentService(Long postId, CommentRequestDto commentrequestDto, User user);

    void editComment(Long commentId, CommentRequestDto commentRequestDto, User user);

    void deleteComment(Long commentId, User user);

    void createReply(Long commentId, User user, CommentRequestDto request);
}
