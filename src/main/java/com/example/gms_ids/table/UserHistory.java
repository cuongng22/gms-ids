package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "USER_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHistory extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "ID", length = 36, nullable = false)
    String id;  // VARCHAR(36), UUID

    @Column(name = "USER_ID", length = 36, nullable = false)
    String userId;

    @Column(name = "FIELD_NAME", length = 20, nullable = false)
    String fieldName;

    @Column(name = "OLD_VALUE", length = 255)
    String oldValue;

    @Column(name = "NEW_VALUE", length = 255)
    String newValue;

    @Column(name = "CHANGED_BY", length = 30)
    String changedBy;

    @Column(name = "CHANGED_DATE")
    java.time.LocalDate changedDate;
}

