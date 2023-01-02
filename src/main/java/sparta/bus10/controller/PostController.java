package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(postrequestDto, userDetails.getUser());
    }

    @GetMapping
    public List<PostResponseDto> getPostAll() {
        return postService.getPostAll();
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPostOne(@PathVariable Long postId) {
        return postService.getPostOne(postId);
    }

    @PatchMapping("/{postId}")
    public void editPost(@PathVariable Long postId, @RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.editPost(postId, postrequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
    }

    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.likePost(postId, userDetails.getUser());
    }

    @PostMapping("/{postId}/unlike")
    public void unlikePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.unlikePost(postId, userDetails.getUser());
    }

}

