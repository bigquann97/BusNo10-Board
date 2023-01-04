package sparta.bus10.service.admin;

import sparta.bus10.dto.CommentRequest;
import sparta.bus10.dto.PostRequest;

public interface AdminService {
    void editPostByAdmin(Long postId, PostRequest postRequest);

    void deletePostByAdmin(Long postId);

    void editCommentByAdmin(Long commentId, CommentRequest commentRequest);

    void deleteCommentByAdmin(Long commentId);
}
