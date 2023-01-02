package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.service.CommentService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public void createCommentService(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        commentService.createCommentService(postId, requestDto);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public void editComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentrequestDto) {
        commentService.editComment(postId, commentId, commentrequestDto);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody String userName) {
        commentService.deleteComment(postId, commentId, userName);
    }

}

