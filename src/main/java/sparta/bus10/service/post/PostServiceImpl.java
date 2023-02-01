package sparta.bus10.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.PostRequest;
import sparta.bus10.dto.PostResponse;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
import sparta.bus10.exception.UserException.AuthorityException;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static sparta.bus10.exception.PostException.PostNotFoundException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void createPost(PostRequest postrequest, User user) {
        System.out.println(user.getUsername());
        Post post = new Post(user, postrequest.getPostTitle(), postrequest.getPostContent());
        postRepository.save(post);
    }

    @Override
    @Transactional
    public List<PostResponse> getPostAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> resultPosts = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPost(post);
            int postLikeCount = likeRepository.countByPostAndComment(post, null);
            PostResponse postResponse = new PostResponse(post, comments,postLikeCount);
            resultPosts.add(postResponse);
        }
        return resultPosts;
    }

    @Override
    @Transactional
    public PostResponse getPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        List<Comment> comment = commentRepository.findByPost(post);
        int postLikeCount = likeRepository.countByPostAndComment(post, null);
        return new PostResponse(post, comment,postLikeCount);
    }

    @Override
    @Transactional
    public void editPost(Long postId, PostRequest postrequest, User user) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
//        validateIfPostWrittenByUser(user, post);
        post.changePost(user, postrequest.getPostTitle(), postrequest.getPostContent());
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        validateIfPostWrittenByUser(user, post);
        likeRepository.deleteByPost(post);
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

    private void validateIfPostWrittenByUser(User user, Post post) {
        if (!post.validateUser(user))
            throw new AuthorityException();
    }

}
