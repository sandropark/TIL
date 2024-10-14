package org.sandro.msaprac.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    protected Long id;
    @Column(updatable = false, nullable = false)
    @CreatedDate
    protected LocalDateTime createdAt;
    @Column(nullable = false)
    @LastModifiedDate
    protected LocalDateTime modifiedAt;
}
