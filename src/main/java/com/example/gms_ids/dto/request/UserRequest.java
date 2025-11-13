package com.example.gms_ids.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserRequest {

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(max = 30)
    String userName;

    @NotBlank(message = "Mật khẩu không được để trống")
    String password;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 50)
    String fullName;

    LocalDate birthDate;

    Integer genderCode;

    @Size(max = 255)
    String address;

    @Size(max = 20)
    String phoneNo;

    @Size(max = 20)
    String faxNo;

    @Email
    @NotBlank(message = "Email không được để trống")
    @Size(max = 50)
    String email;

    @Size(max = 20)
    String identityNo;

    Integer identityTypeCode;

    LocalDate identityIssuedDate;

    @Size(max = 20)
    String identityIssuedPlace;

    @NotNull(message = "Department ID không được để trống")
    Long departmentId;

    @NotNull(message = "Position Code không được để trống")
    Integer positionCode;

    Integer statusFlg;

    Long companyProfileId;

    @Size(max = 255)
    String description;
}
