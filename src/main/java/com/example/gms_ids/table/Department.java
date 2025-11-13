package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "DEPARTMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department extends BaseEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @UuidGenerator
    String id; // NUMBER(10), PK

    @Column(name = "DEPARTMENT_NAME", length = 50, nullable = false)
    String departmentName;

    @Column(name = "DESCRIPTION", length = 255)
    String description;
}