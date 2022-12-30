package sparta.bus10.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.bus10.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    @PostMapping("/post")
    public void createPost(){

    }
}
