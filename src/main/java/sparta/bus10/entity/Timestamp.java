package sparta.bus10.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamp {

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long createdUserId;

    private Long modifiedUserId;

    protected void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    protected void updateModifiedAt() {
        this.modifiedAt = LocalDateTime.now();
    }

    protected void updateCreatedBy(Long id) {
        this.createdUserId = id;
        this.modifiedUserId = id;
    }

    protected void updateModifiedBy(Long id) {
        this.modifiedUserId = id;
    }


}
