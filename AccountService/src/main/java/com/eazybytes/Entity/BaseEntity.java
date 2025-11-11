package com.eazybytes.Entity;

import com.eazybytes.audit.AuditAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditAwareImpl.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(insertable  = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate updatedAt;
}
