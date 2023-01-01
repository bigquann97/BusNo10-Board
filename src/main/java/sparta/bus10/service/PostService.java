package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.Comment;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createPost(PostRequestDto postrequestDto) {
        postRepository.save(new Post(postrequestDto.getUserName(), postrequestDto.getPostTitle(), postrequestDto.getPostContent()));
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
    public void editPost(Long postId, PostRequestDto postrequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.getUsername().equals(postrequestDto.getUserName())) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        post.changePost(postrequestDto.getUserName(), postrequestDto.getPostTitle(), postrequestDto.getPostContent());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, PostRequestDto postrequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        if (!post.getUsername().equals(postrequestDto.getUserName())) {
            throw new IllegalArgumentException("유저의 이름이 일치하지 않습니다.");
        }
        postRepository.delete(post);
    }

}
