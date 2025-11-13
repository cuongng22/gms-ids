package com.example.gms_ids.dto.request;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyProfileRequest {
    @NotBlank(message = "Tên công ty không được để trống")
    @Size(max = 255)
    String companyName;

    @Size(max = 10)
    String stockCode;

    @Size(max = 10)
    String stockExchange;

    @Size(max = 255)
    String description;
}
