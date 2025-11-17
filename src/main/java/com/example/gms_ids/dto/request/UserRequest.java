package com.example.gms_ids.dto.request;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserRequest {
    String userName;
    String password;
    String fullName;
    LocalDate birthDate;
    Integer genderCode;
    String address;
    String phoneNo;
    String faxNo;
    String email;
    String identityNo;
    Integer identityTypeCode;
    LocalDate identityIssuedDate;
    String identityIssuedPlace;
    Long departmentId;
    Integer positionCode;
    Integer statusFlg;
    Long companyProfileId;
    String description;
}
