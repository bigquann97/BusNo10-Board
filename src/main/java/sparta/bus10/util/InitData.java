package sparta.bus10.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sparta.bus10.entity.*;
import sparta.bus10.repository.CommentRepository;
import sparta.bus10.repository.LikeRepository;
import sparta.bus10.repository.PostRepository;
import sparta.bus10.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User sparta = new User("sparta", passwordEncoder.encode("sparta"), UserRoleEnum.ADMIN);
        User gwanho = new User("gwanho", passwordEncoder.encode("gwanho"), UserRoleEnum.USER);
        User songmi = new User("songmi", passwordEncoder.encode("songmi"), UserRoleEnum.USER);
        User jaewon = new User("jaewon", passwordEncoder.encode("jaewon"), UserRoleEnum.USER);
        User imsoo = new User("imsoo", passwordEncoder.encode("imsoo"), UserRoleEnum.USER);

        userRepository.save(sparta);
        userRepository.save(gwanho);
        userRepository.save(songmi);
        userRepository.save(jaewon);
        userRepository.save(imsoo);

        Post post1 = new Post(gwanho, "안녕하세요 김관호입니다.", "여러분 다들 새해 복 많이받으세요 ~");
        Post post2 = new Post(songmi, "안녕하세요 김송미입니다.", "떡국은 잡수셨나요?");
        Post post3 = new Post(jaewon, "안녕하세요 이재원입니다.", "다들 한 살 더 드신거 축하드립니다.");
        Post post4 = new Post(imsoo, "안녕하세요 임수입니다.", "내일배움캠프 화이팅~!!");
        Post post5 = new Post(sparta, "안녕하세요 관리자 르탄이입니다.", "이상한 글 쓰면 제가 삭제합니다!");
        Iterable<Post> posts = new ArrayList<>(List.of(
                post1, post2, post3, post4, post5
        ));

        postRepository.saveAll(posts);

        Comment comment1 = new Comment(post1, songmi, "송미 발자국 꾸욱");
        Comment comment2 = new Comment(post1, jaewon, "재원 메롱 하고 감");
        Comment comment3 = new Comment(post1, imsoo, "수 아임 수");
        Comment comment13 = new Comment(post1, sparta, "나는 관리자 르탄이");

        Comment comment4 = new Comment(post2, gwanho, "김관호랑이");
        Comment comment5 = new Comment(post2, jaewon, "재원 메롱 하고 감");
        Comment comment6 = new Comment(post2, imsoo, "수 아임 수");
        Comment comment14 = new Comment(post2, sparta, "나는 관리자 르탄이");

        Comment comment7 = new Comment(post3, gwanho, "김관호랑이");
        Comment comment8 = new Comment(post3, songmi, "송미 발자국 꾸욱");
        Comment comment9 = new Comment(post3, imsoo, "수 아임 수");
        Comment comment15 = new Comment(post3, sparta, "나는 관리자 르탄이");

        Comment comment10 = new Comment(post4, gwanho, "수 아임 수");
        Comment comment11 = new Comment(post4, songmi, "송미 발자국 꾸욱");
        Comment comment12 = new Comment(post4, jaewon, "재원 메롱 하고 감");
        Comment comment16 = new Comment(post4, sparta, "나는 관리자 르탄이");

        Comment comment17 = new Comment(post1, gwanho, "댓글 남겨주셔서 감사합니다~", true, 1L);
        Comment comment18 = new Comment(post1, imsoo, "저도 놀고 갑니다", true, 1L);

        Iterable<Comment> comments = new ArrayList<>(List.of(
                comment1, comment2, comment3, comment4, comment5, comment6, comment7, comment8, comment9, comment10, comment11, comment12,
                comment13, comment14, comment15, comment16, comment17, comment18
        ));

        commentRepository.saveAll(comments);

        Like like1 = new Like(post1, null, gwanho);
        Like like2 = new Like(post1, null, imsoo);
        Like like3 = new Like(post1, null, songmi);
        Like like4 = new Like(post1, comment1, imsoo);
        Like like5 = new Like(post1, comment2, imsoo);

        ArrayList<Like> likes = new ArrayList<>(List.of(
                like1, like2, like3, like4, like5
        ));

        likeRepository.saveAll(likes);
    }

}
