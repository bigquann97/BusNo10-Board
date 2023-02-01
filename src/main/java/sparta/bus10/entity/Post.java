package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import sparta.bus10.security.UserDetailsImpl;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    public Post(User user, String requestPostTitle, String requestPostContent) {
        this.user = user;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

    public void changePost(User user, String requestPostTitle, String requestPostContent) {
        this.user = user;
        this.postTitle = requestPostTitle;
        this.postContent = requestPostContent;
    }

    public boolean validateUser(User user) {
        return this.user.validateUser(user);
    }

    @PrePersist
    public void prePersist() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetailsImpl targetUser = (UserDetailsImpl) principal;
            Long id = targetUser.getUser().getId();
            super.updateCreatedAt();
            super.updateModifiedAt();
            super.updateCreatedBy(id);
            super.updateModifiedBy(id);
        } else {
            throw new AccessDeniedException("사용자 정보가 없습니다.");
        }
    }

    @PreUpdate
    public void preUpdate() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetailsImpl targetUser = (UserDetailsImpl) principal;
            Long id = targetUser.getUser().getId();
            super.updateModifiedAt();
            super.updateModifiedBy(id);
        } else {
            throw new AccessDeniedException("사용자 정보가 없습니다.");
        }
    }

}
