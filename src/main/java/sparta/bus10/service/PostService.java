package sparta.bus10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.bus10.dto.PostRequestDto;
import sparta.bus10.dto.PostResponseDto;
import sparta.bus10.entity.Post;
import sparta.bus10.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public void createService(PostRequestDto requestDto) {
        postRepository.save(new Post(requestDto));
    }

    public List<Post> getPostAll(){
        return postRepository.findAll();
    }


    public Post getPostOne(Long postId){
        return postRepository.findById(postId).orElseThrow(
            ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }
    public void editPost(Long postId ,PostRequestDto postrequestDto) {
       Post post = postRepository.findByPostId(postId);
       post.changePost(postrequestDto);
       postRepository.save(post);
    }
    public void deletePost(Long postId){
        Post post = postRepository.findByPostId(postId);
        postRepository.delete(post);
    }

}
