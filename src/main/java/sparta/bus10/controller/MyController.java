package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sparta.bus10.dto.CommentResponseDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.myInfo.MyService;

import java.util.List;

@RequestMapping("/api/my-info")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public class MyController {

    private final MyService myService;

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> myPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myService.getMyPosts(userDetails.getUser());
    }

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> myComments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myService.getMyComments(userDetails.getUser());
    }

    @GetMapping("/like-posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> myLikedPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myService.getMyLikedPosts(userDetails.getUser());
    }

    @GetMapping("/like-comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> myLikedComments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myService.getMyLikedComments(userDetails.getUser());
    }

}
