package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserAuditLogRequest;
import com.example.gms_ids.table.UserAuditLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserAuditLogService {
    Page<UserAuditLog> findAll(int page, int size);
    UserAuditLog findById(Long id) throws Exception;
    UserAuditLog create(UserAuditLogRequest request) throws Exception;
    UserAuditLog update(Long id, UserAuditLogRequest request) throws Exception;
    void delete(Long id) throws Exception;
    void deleteMultiple(List<Long> ids) throws Exception;
    void saveAuditLog(HttpServletRequest httpRequest, String httpMethod, String queryParams, Object requestBody, String sourceService);
}


