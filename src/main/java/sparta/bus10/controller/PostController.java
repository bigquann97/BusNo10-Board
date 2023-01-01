package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void createPost(@RequestBody PostRequestDto postrequestDto) {
        postService.createPost(postrequestDto);
    }


    @GetMapping("/posts")
    public List<PostResponseDto> getPostAll() {
        return postService.getPostAll();
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPostOne(@PathVariable Long postId) {
        return postService.getPostOne(postId);
    }

    @PutMapping("/posts/{postId}")
    public void editPost(@PathVariable Long postId, @RequestBody PostRequestDto postrequestDto) {
        postService.editPost(postId, postrequestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody PostRequestDto postrequestDto) {
        postService.deletePost(postId, postrequestDto);
    }

}

