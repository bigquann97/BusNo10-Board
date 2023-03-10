package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.CommentRequest;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.comment.CommentService;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommentService(
            @RequestParam Long postId,
            @RequestBody CommentRequest requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createCommentService(postId, requestDto, userDetails.getUser());
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentrequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.editComment(commentId, commentrequest, userDetails.getUser());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails.getUser());
    }

    @PostMapping("/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReply(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequest request
    ) {
        commentService.createReply(commentId, userDetails.getUser(), request);
    }

}

