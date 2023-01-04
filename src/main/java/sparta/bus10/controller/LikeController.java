package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.like.LikeService;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void likePost(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likePost(id, userDetails.getUser());
    }

    @DeleteMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public void unlikePost(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.unlikePost(id, userDetails.getUser());
    }

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void likeComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likeComment(id, userDetails.getUser());
    }

    @DeleteMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public void unlikeComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.unlikeComment(id, userDetails.getUser());
    }

}
