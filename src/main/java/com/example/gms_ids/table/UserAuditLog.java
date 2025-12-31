package com.example.gms_ids.table;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "USER_AUDIT_LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "USER_ID", length = 40, nullable = false)
    String userId;

    @Column(name = "USER_NAME", length = 50, nullable = false)
    String userName;

    @Column(name = "USER_AGENT", length = 500, nullable = false)
    String userAgent;

    @Column(name = "CLIENT_IP", length = 45, nullable = false)
    String clientIp;

    @Column(name = "HTTP_METHOD", length = 10, nullable = false)
    String httpMethod;

    @Lob
    @Column(name = "QUERY_PARAMS", nullable = false)
    String queryParams;

    @Lob
    @Column(name = "REQUEST_BODY", nullable = false)
    String requestBody;

    @Column(name = "SOURCE_SERVICE", length = 100, nullable = false)
    String sourceService;

    @Column(name = "CREATED_DATE", nullable = false)
    LocalDate createdDate;
}




