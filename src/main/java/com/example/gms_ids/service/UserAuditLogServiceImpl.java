package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserAuditLogRequest;
import com.example.gms_ids.repository.UserAuditLogRepository;
import com.example.gms_ids.table.UserAuditLog;
import com.example.gms_ids.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuditLogServiceImpl implements UserAuditLogService {
    private final UserAuditLogRepository userAuditLogRepository;

    @Override
    public Page<UserAuditLog> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userAuditLogRepository.findAll(pageable);
    }

    @Override
    public UserAuditLog findById(Long id) throws Exception {
        Optional<UserAuditLog> log = userAuditLogRepository.findById(id);
        if (!log.isPresent()) {
            throw new Exception("Khong ton tai audit log");
        }
        return log.get();
    }

    @Override
    public UserAuditLog create(UserAuditLogRequest request) throws Exception {
        UserAuditLog log = new UserAuditLog();
        log.setUserId(request.getUserId());
        log.setUserName(request.getUserName());
        log.setUserAgent(request.getUserAgent());
        log.setClientIp(request.getClientIp());
        log.setHttpMethod(request.getHttpMethod());
        log.setQueryParams(request.getQueryParams());
        log.setRequestBody(request.getRequestBody());
        log.setSourceService(request.getSourceService());
        log.setCreatedDate(LocalDate.now());
        return userAuditLogRepository.save(log);
    }

    @Override
    public UserAuditLog update(Long id, UserAuditLogRequest request) throws Exception {
        Optional<UserAuditLog> logOpt = userAuditLogRepository.findById(id);
        if (!logOpt.isPresent()) {
            throw new Exception("Khong ton tai audit log");
        }
        UserAuditLog log = logOpt.get();
        if (request.getUserId() != null) {
            log.setUserId(request.getUserId());
        }
        if (request.getUserName() != null) {
            log.setUserName(request.getUserName());
        }
        if (request.getUserAgent() != null) {
            log.setUserAgent(request.getUserAgent());
        }
        if (request.getClientIp() != null) {
            log.setClientIp(request.getClientIp());
        }
        if (request.getHttpMethod() != null) {
            log.setHttpMethod(request.getHttpMethod());
        }
        if (request.getQueryParams() != null) {
            log.setQueryParams(request.getQueryParams());
        }
        if (request.getRequestBody() != null) {
            log.setRequestBody(request.getRequestBody());
        }
        if (request.getSourceService() != null) {
            log.setSourceService(request.getSourceService());
        }
        return userAuditLogRepository.save(log);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<UserAuditLog> log = userAuditLogRepository.findById(id);
        if (!log.isPresent()) {
            throw new Exception("Khong ton tai audit log");
        }
        userAuditLogRepository.deleteById(id);
    }

    @Override
    public void deleteMultiple(List<Long> ids) throws Exception {
        if (ids == null || ids.isEmpty()) {
            throw new Exception("Danh sach id khong duoc rong");
        }
        List<UserAuditLog> logs = userAuditLogRepository.findAllById(ids);
        if (logs.size() != ids.size()) {
            throw new Exception("Mot so audit log khong ton tai");
        }
        userAuditLogRepository.deleteAllById(ids);
    }

    @Override
    public void saveAuditLog(HttpServletRequest httpRequest, String httpMethod, String queryParams, Object requestBody, String sourceService) {
        try {
            UserAuditLog log = new UserAuditLog();
            log.setUserId(RequestUtil.getUserId(httpRequest));
            log.setUserName(RequestUtil.getUserName(httpRequest));
            log.setUserAgent(RequestUtil.getUserAgent(httpRequest));
            log.setClientIp(RequestUtil.getClientIp(httpRequest));
            log.setHttpMethod(httpMethod);
            log.setQueryParams(queryParams != null ? queryParams : "");
            log.setRequestBody(RequestUtil.getRequestBodyAsString(requestBody));
            log.setSourceService(sourceService);
            log.setCreatedDate(LocalDate.now());
            userAuditLogRepository.save(log);
        } catch (Exception e) {
        }
    }
}


