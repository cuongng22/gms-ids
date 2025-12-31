package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "SYS_PARAMETERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SysParameter extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "ID", length = 36, nullable = false)
    String id;

    @Column(name = "PARA_DOMAIN", length = 50, nullable = false)
    String paraDomain;

    @Column(name = "PARA_CD", length = 10, nullable = false)
    String paraCd;

    @Column(name = "PARA_VALUE", length = 1024, nullable = false)
    String paraValue;

    @Column(name = "DESCRIPTION", length = 255)
    String description;

    @Column(name = "ACTIVE_FLG", nullable = false)
    Integer activeFlg = 1;
}

