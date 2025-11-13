package com.example.gms_ids.table;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Column(name = "CREATED_BY", length = 30)
    String createdBy;

    @Column(name = "CREATED_DATE")
    LocalDate createdDate;

    @Column(name = "LAST_UPDATED_BY", length = 30)
    String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE")
    LocalDate lastUpdatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedDate = LocalDate.now();
    }
}

