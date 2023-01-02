package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.bus10.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin") // root
public class AdminController {

    private final AdminService adminService;

    // http://localhost:8080/api/admin/posts/1 - Header Token값을 넣어서 보내줘요 Authorization : Bearer aivbi3
    // 어드민 권한 게시글 수정
    @PutMapping("/posts/{postId}") // Spring Annocation
    @PreAuthorize('hasRole("ROLE_ADMIN")')
    public void deletePostByAdmin(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        adminService.deletePostByAdmin(postId);
    }

    // 어드민 권한 게시글 삭제 - editPostByAdmin


    // 어드민 권한 댓글 삭제 - deleteCommentByAdmin


    // 어드민 권한 댓글 수정 - editCommentByAdmin


}
