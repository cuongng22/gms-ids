package com.example.gms_ids.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuditLogRequest {
    Long id;
    String userId;
    String userName;
    String userAgent;
    String clientIp;
    String httpMethod;
    String queryParams;
    String requestBody;
    String sourceService;
}




