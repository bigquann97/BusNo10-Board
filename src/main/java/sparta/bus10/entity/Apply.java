package sparta.bus10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column
    private int accept = 0; // 0:대기중 1:수락 2:거부

    public Apply(User user) {
        this.user = user;
    }
}
