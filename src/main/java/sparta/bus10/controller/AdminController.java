package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin") // root
public class AdminController {

    private final AdminService adminService;

    // http://localhost:8080/api/admin/posts/1 - Header Token값을 넣어서 보내줘요 Authorization : Bearer aivbi3
    // 어드민 권한 게시글 삭제
    @DeleteMapping ("/posts/{postId}") // Spring Annocation
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePostByAdmin(@PathVariable Long postId) {
        adminService.deletePostByAdmin(postId);
    }

    // 어드민 권한 게시글 수정 - editPostByAdmin
    @PutMapping("/posts/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editPostByAdmin(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){
        adminService.editPostByAdmin(postId, postRequestDto);
    }

    // 어드민 권한 댓글 삭제 - deleteCommentByAdmin
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCommentByAdmin(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        adminService.deleteCommentByAdmin(postId, commentId);
    }

    // 어드민 권한 댓글 수정 - editCommentByAdmin
    @PutMapping("/posts/{postId}/comments/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
            public void editCommentByAdmin(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails, @RequestBody CommentRequestDto commentRequestDto) {
        adminService.editCommentByAdmin(postId, commentId, commentRequestDto);
    }

}
