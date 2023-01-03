package sparta.bus10.service.myInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentResponseDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Like;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyServiceImpl implements MyService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getMyPosts(User user) {
        List<Post> posts = postRepository.findPostsByUserOrderByCreatedAt(user);
        List<PostResponseDto> result = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPost(post);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments);
            result.add(postResponseDto);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getMyComments(User user) {
        List<Comment> comments = commentRepository.findByUser(user);
        List<CommentResponseDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto(comment);
            commentDtos.add(dto);
        }
        return commentDtos;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getMyLikedPosts(User user) {
        List<Like> postLikes = likeRepository.findByUserAndComment(user, null);
        List<PostResponseDto> response = new ArrayList<>();
        for (Like postLike : postLikes) {
            Post post = postLike.getPost();
            List<Comment> comments = commentRepository.findByPost(post);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments);
            response.add(postResponseDto);
        }
        return response;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getMyLikedComments(User user) {
        List<Like> commentLikes = likeRepository.findByUserAndPost(user, null);
        List<CommentResponseDto> response = new ArrayList<>();
        for (Like commentLike : commentLikes) {
            Comment comment = commentLike.getComment();
            CommentResponseDto dto = new CommentResponseDto(comment);
            response.add(dto);
        }
        return response;
    }
}
