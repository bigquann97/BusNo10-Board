package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.service.admin.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping ("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostByAdmin(@PathVariable Long postId) {
        adminService.deletePostByAdmin(postId);
    }

    @PatchMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void editPostByAdmin(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){
        adminService.editPostByAdmin(postId, postRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentByAdmin(@PathVariable Long commentId) {
        adminService.deleteCommentByAdmin(commentId);
    }

    @PatchMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void editCommentByAdmin(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        adminService.editCommentByAdmin(commentId, commentRequestDto);
    }

}
