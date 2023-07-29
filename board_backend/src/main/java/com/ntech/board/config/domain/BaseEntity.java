package com.ntech.board.config.domain;

import com.ntech.board.config.status.BaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public void remove(){
        this.status = BaseStatus.INACTIVE;
    }

    protected void changeStatus(BaseStatus status){
        this.status = status;
    }
}
