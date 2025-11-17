package com.example.gms_ids.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "ID", length = 36, nullable = false)
    String id;   // UUID

    @Column(name = "USER_NAME", length = 30, nullable = false)
    String userName;

    @Column(name = "PASSWORD", length = 128)
    String password;

    @Column(name = "FULL_NAME", length = 50)
    String fullName;

    @Column(name = "BIRTH_DATE")
    LocalDate birthDate;

    @Column(name = "GENDER_CD")
    Integer genderCode;   // lookup

    @Column(name = "ADDRESS", length = 255)
    String address;

    @Column(name = "PHONE_NO", length = 20)
    String phoneNo;

    @Column(name = "FAX_NO", length = 20)
    String faxNo;

    @Email
    @NotBlank
    @Column(name = "EMAIL", length = 50, nullable = false)
    String email;

    @Column(name = "IDENTITY", length = 20)
    String identityNo;

    @Column(name = "IDENTITY_TYPE_CD")
    Integer identityTypeCode;   // lookup

    @Column(name = "IDENTITY_ISSUED_DATE")
    LocalDate identityIssuedDate;

    @Column(name = "IDENTITY_ISSUED_PLACE", length = 20)
    String identityIssuedPlace;

    @Column(name = "DEPARTMENT_ID", nullable = false)
    Long departmentId;  // FK → DEPARTMENTS

    @Column(name = "POSITION_CD")
    Integer positionCode;  // lookup

    @Column(name = "STATUS_FLG")
    Integer statusFlg;  // 1-active, 0-inactive

    @Column(name = "COMPANY_PROFILE_ID")
    Long companyProfileId;   // FK

    @Column(name = "DESCRIPTION", length = 255)
    String description;

}