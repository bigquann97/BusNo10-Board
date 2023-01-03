package sparta.bus10.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.entity.User;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void createPost(PostRequestDto postrequestDto, User user) {
        System.out.println(user.getUsername());
        Post post = new Post(user, postrequestDto.getPostTitle(), postrequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public List<PostResponseDto> getPostAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> resultPosts = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPost(post);
            int postLikeCount = likeRepository.countByPost(post);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments,postLikeCount);
            resultPosts.add(postResponseDto);
        }
        return resultPosts;
    }

    @Transactional
    public PostResponseDto getPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        List<Comment> comment = commentRepository.findByPost(post);
        int postLikeCount = likeRepository.countByPost(post);
        return new PostResponseDto(post, comment,postLikeCount);
    }

    @Transactional
    public void editPost(Long postId, PostRequestDto postrequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.validateUser(user)) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        post.changePost(user, postrequestDto.getPostTitle(), postrequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.validateUser(user)) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        likeRepository.deleteByPost(post);
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

}
