package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "LOOKUP_VALUES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LookupValue extends BaseEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @UuidGenerator
    String id; // NUMBER(10), PK

    @Column(name = "LOOKUP_VALUE", length = 255, nullable = false)
    String lookupValue;

    @Column(name = "LOOKUP_CD", nullable = false)
    Integer lookupCode;

    @Column(name = "LOOKUP_GROUP", length = 20)
    String lookupGroup;

    @Column(name = "STATUS_FLG")
    Integer statusFlg; // 1-active, 0-inactive

    @Column(name = "DESCRIPTION", length = 255)
    String description;
}

