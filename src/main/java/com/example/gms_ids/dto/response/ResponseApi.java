package com.example.gms_ids.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseApi {
    Boolean success;
    Long statusCode;
    String message;
    LocalDateTime timestamp;
    Object data;
}
