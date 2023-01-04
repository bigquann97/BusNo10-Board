package sparta.bus10.service.myInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.CommentResponse;
import sparta.bus10.dto.PostResponse;
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

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getMyPosts(User user) {
        List<Post> posts = postRepository.findPostsByUserOrderByCreatedAt(user);
        List<PostResponse> result = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPost(post);
            int postLikeCount = likeRepository.countByPostAndComment(post, null);
            PostResponse postResponse = new PostResponse(post, comments,postLikeCount);
            result.add(postResponse);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getMyComments(User user) {
        List<Comment> comments = commentRepository.findByUser(user);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse dto = new CommentResponse(comment);
            commentResponses.add(dto);
        }
        return commentResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getMyLikedPosts(User user) {
        List<Like> postLikes = likeRepository.findByUserAndCommentNull(user);
        List<PostResponse> response = new ArrayList<>();
        for (Like postLike : postLikes) {
            Post post = postLike.getPost();
            List<Comment> comments = commentRepository.findByPost(post);
            int postLikeCount = likeRepository.countByPostAndComment(post, null);
            PostResponse postResponse = new PostResponse(post, comments,postLikeCount);
            response.add(postResponse);
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getMyLikedComments(User user) {
        List<Like> commentLikes = likeRepository.findByUserAndCommentNotNull(user);
        List<CommentResponse> response = new ArrayList<>();
        for (Like commentLike : commentLikes) {
            Comment comment = commentLike.getComment();
            CommentResponse dto = new CommentResponse(comment);
            response.add(dto);
        }
        return response;
    }
}
