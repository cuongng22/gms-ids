package com.example.gms_ids.controller;

import com.example.gms_ids.common.ApiStatus;
import com.example.gms_ids.dto.request.DeleteRequest;
import com.example.gms_ids.dto.request.UserAuditLogRequest;
import com.example.gms_ids.dto.response.ApiResponse;
import com.example.gms_ids.service.UserAuditLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-audit-logs")
@RequiredArgsConstructor
@Tag(name = "User Audit Log API", description = "Quản lý audit log người dùng")
@Slf4j
public class UserAuditLogController {

    private final UserAuditLogService userAuditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userAuditLogService.findAll(page, size));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Lấy audit log theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userAuditLogService.findById(id));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Tạo audit log mới")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody UserAuditLogRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userAuditLogService.create(request));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Cập nhật audit log")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UserAuditLogRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userAuditLogService.update(id, request));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Xóa audit log theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = new ApiResponse();
        try {
            userAuditLogService.delete(id);
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage("Xóa audit log thành công");
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Xóa nhiều audit log")
    @PostMapping("/delete-multiple")
    public ResponseEntity<ApiResponse> deleteMultiple(@RequestBody DeleteRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            if (request.getIds() == null || request.getIds().isEmpty()) {
                response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
                response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
                response.setMessage("Danh sach id khong duoc rong");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            java.util.List<Long> longIds = request.getIds().stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            userAuditLogService.deleteMultiple(longIds);
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage("Xóa " + request.getIds().size() + " audit log thành công");
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




