package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.CommentService;
import sparta.bus10.service.LikeService;
import sparta.bus10.service.PostService;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post")
    public void likePost(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likePost(id, userDetails.getUser());
    }

    @DeleteMapping("/post")
    public void unlikePost(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.unlikePost(id, userDetails.getUser());
    }

    @PostMapping("/comment")
    public void likeComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likeComment(id, userDetails.getUser());
    }

    @DeleteMapping("/comment")
    public void unlikeComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.unlikeComment(id, userDetails.getUser());
    }
}