package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.PostRequestDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void createPost(PostRequestDto postrequestDto, User user) {
        System.out.println(user.getUsername());
        Post post = new Post(user.getUsername(), postrequestDto.getPostTitle(), postrequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public List<PostResponseDto> getPostAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> resultPosts = new ArrayList<>();
        for (Post post : posts) {
            Long postId = post.getPostId();
            List<Comment> comments = commentRepository.findByPostId(postId);
            PostResponseDto postResponseDto = new PostResponseDto(post, comments);
            resultPosts.add(postResponseDto);
        }
        return resultPosts;
    }

    @Transactional
    public PostResponseDto getPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        List<Comment> comment = commentRepository.findByPostId(postId);
        PostResponseDto postResponseDto = new PostResponseDto(post, comment);
        return postResponseDto;
    }

    @Transactional
    public void editPost(Long postId, PostRequestDto postrequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        post.changePost(user.getUsername(), postrequestDto.getPostTitle(), postrequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        postRepository.delete(post);
    }

    @Transactional
    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Optional<Like> found = likeRepository.findByPostIdAndUserId(post.getPostId(), user.getId());

        if(found.isPresent()){
            throw new IllegalArgumentException("이미 좋아요 한 게시물입니다.");
        }
        Like like = new Like(post.getPostId(), user.getId());

        likeRepository.save(like);
    }

    @Transactional
    public void unlikePost(Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Optional<Like> like = likeRepository.findByPostIdAndUserId(post.getPostId(), user.getId());
        if(!like.isPresent()){
            throw new IllegalArgumentException("좋아요를 하지 않은 게시물입니다.");
        }

        likeRepository.delete(like.get());
    }
}
