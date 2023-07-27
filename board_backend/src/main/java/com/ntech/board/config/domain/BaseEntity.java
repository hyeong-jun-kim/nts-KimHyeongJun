package com.ntech.board.config.domain;

import com.ntech.board.config.status.BaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.ACTIVE; // 기본 설정은 ACTIVE

    protected void setStatus(BaseStatus status){
        this.status = status;
    }
}
