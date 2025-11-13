package com.example.gms_ids.dto.request;
import jakarta.validation.constraints.*;
public class DepartmentRequest {
    @NotBlank(message = "Tên đơn vị không được để trống")
    @Size(max = 50)
    String departmentName;
    @Size(max = 255)
    String description;
}
