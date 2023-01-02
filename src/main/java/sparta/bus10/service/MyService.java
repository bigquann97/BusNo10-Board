package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.bus10.dto.CommentResponseDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public List<PostResponseDto> getMyPosts(String username) {
        List<Post> posts = postRepository.findPostsByUsernameOrderByCreatedAt(username);
        List<PostResponseDto> result = new ArrayList<>();
        for (Post post : posts) {
            Long postId = post.getPostId();
            List<Comment> comments = commentRepository.findByPostId(postId);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments);
            result.add(postResponseDto);
        }
        return result;
    }

    public List<CommentResponseDto> getMyComments(String username) {
        List<Comment> comments = commentRepository.findByUsername(username);
        List<CommentResponseDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto(comment);
            commentDtos.add(dto);
        }
        return commentDtos;
    }

    public List<PostResponseDto> getMyLikedPosts(Long userId) {
        List<Like> postLikes = likeRepository.findByUserIdAndCommentId(userId, null);
        List<PostResponseDto> response = new ArrayList<>();
        for (Like postLike : postLikes) {
            Long postId = postLike.getPostId();
            Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시물 없음"));
            List<Comment> comments = commentRepository.findByPostId(postId);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments);
            response.add(postResponseDto);
        }
        return response;
    }

    public List<CommentResponseDto> getMyLikedComments(Long userId) {
        List<Like> commentLikes = likeRepository.findByUserIdAndPostId(userId, null);
        List<CommentResponseDto> response = new ArrayList<>();
        for (Like commentLike : commentLikes) {
            Long commentId = commentLike.getCommentId();
            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
            CommentResponseDto dto = new CommentResponseDto(comment);
            response.add(dto);
        }
        return response;
    }
}
