package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostRequestDto postrequestDto) {
        postService.createPost(postrequestDto);
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
    public void editPost(@PathVariable Long postId, @RequestBody PostRequestDto postrequestDto) {
        postService.editPost(postId, postrequestDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody PostRequestDto postrequestDto) {
        postService.deletePost(postId, postrequestDto);
    }

}

