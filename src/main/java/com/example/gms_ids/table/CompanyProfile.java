package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "COMPANY_PROFILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyProfile extends BaseEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @UuidGenerator
    String id; // NUMBER(10), PK

    @Column(name = "COMPANY_NAME", length = 255, nullable = false)
    String companyName;

    @Column(name = "STOCK_CODE", length = 10)
    String stockCode;

    @Column(name = "STOCK_EXCHANGE", length = 10)
    String stockExchange;
}

