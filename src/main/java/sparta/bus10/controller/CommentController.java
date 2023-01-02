package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.dto.CommentRequestDto;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void createCommentService(
            @RequestParam Long postId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createCommentService(postId, requestDto, userDetails.getUser());
    }

    @PatchMapping("/{commentId}")
    public void editComment(
//            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentrequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.editComment(commentId, commentrequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
//            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails.getUser());
    }

    @PostMapping("/{commentId}")
    public void createReply(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto request
    ) {
        commentService.createReply(commentId, userDetails.getUser(), request);
    }

}

