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
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping ("/posts/{postId}")
    public void deletePostByAdmin(@PathVariable Long postId) {
        adminService.deletePostByAdmin(postId);
    }

    @PatchMapping("/posts/{postId}")
    public void editPostByAdmin(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){
        adminService.editPostByAdmin(postId, postRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteCommentByAdmin(@PathVariable Long commentId) {
        adminService.deleteCommentByAdmin(commentId);
    }

    @PatchMapping("/comments/{commentId}")
    public void editCommentByAdmin(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        adminService.editCommentByAdmin(commentId, commentRequestDto);
    }

}
