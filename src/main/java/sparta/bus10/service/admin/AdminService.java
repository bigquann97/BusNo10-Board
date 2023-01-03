package sparta.bus10.service.admin;

import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.dto.PostRequestDto;

public interface AdminService {
    void editPostByAdmin(Long postId, PostRequestDto postRequestDto);

    void deletePostByAdmin(Long postId);

    void editCommentByAdmin(Long commentId, CommentRequestDto commentRequestDto);

    void deleteCommentByAdmin(Long commentId);
}
