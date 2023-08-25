package com.lecture.reservation.common.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by HoYoung on 2023/04/07.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @PrePersist
    private void prePersist() {

    }

    @PostPersist
    private void postPersist() {

    }

    @PreUpdate
    private void preUpdate() {

    }

    @PostUpdate
    private void postUpdate() {

    }

    @PreRemove
    private void preRemove() {
    }

    @PostRemove
    private void postRemove() {
    }

    @PostLoad
    private void postLoad() {
    }
}
