package uz.ppdiary.pp_diary.entity.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditing {
    @CreatedBy
    @Column(updatable = false)
    private Long created_By;

    @LastModifiedBy
    private Long updated_By;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp createAt;

    @LastModifiedDate
    private Timestamp updateAt;
}
