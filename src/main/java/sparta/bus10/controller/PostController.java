package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.entity.Post;
import sparta.bus10.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody PostRequestDto requestDto) {
        postService.createService(requestDto);
    }

    @GetMapping("/posts")
    public List<Post> getPostAll(){
        return postService.getPostAll();
    }
    @GetMapping("/post/{postId}")
    public Post getPostOne(@PathVariable Long postId){
        return postService.getPost(postId);
    }
}

