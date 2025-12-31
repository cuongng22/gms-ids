package com.example.gms_ids.controller;

import com.example.gms_ids.common.ApiStatus;
import com.example.gms_ids.dto.request.DeleteRequest;
import com.example.gms_ids.dto.request.SysParameterSearchRequest;
import com.example.gms_ids.dto.request.SysParameterRequest;
import com.example.gms_ids.dto.response.ApiResponse;
import com.example.gms_ids.service.SysParameterService;
import com.example.gms_ids.service.UserAuditLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys-parameters")
@RequiredArgsConstructor
@Tag(name = "Sys Parameter API", description = "Quản lý tham số hệ thống")
@Slf4j
public class SysParameterController {

    private final SysParameterService sysParameterService;
    private final UserAuditLogService userAuditLogService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(sysParameterService.findAll(page, size));
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

    @Operation(summary = "Tìm kiếm parameter với các filter")
    @PostMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestBody SysParameterSearchRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(sysParameterService.search(request));
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

    @Operation(summary = "Lấy parameter theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable String id) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(sysParameterService.findById(id));
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

    @Operation(summary = "Tạo parameter mới")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody SysParameterRequest request, HttpServletRequest httpRequest) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(sysParameterService.create(request));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            
            userAuditLogService.saveAuditLog(httpRequest, "POST", "", request, "sys-parameter");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Cập nhật parameter")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @RequestBody SysParameterRequest request, HttpServletRequest httpRequest) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(sysParameterService.update(id, request));
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage(ApiStatus.SUCCESS.getMessage());
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            
            userAuditLogService.saveAuditLog(httpRequest, "PUT", "id=" + id, request, "sys-parameter");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Xóa parameter theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id, HttpServletRequest httpRequest) {
        ApiResponse response = new ApiResponse();
        try {
            sysParameterService.delete(id);
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage("Xóa parameter thành công");
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            
            userAuditLogService.saveAuditLog(httpRequest, "DELETE", "id=" + id, null, "sys-parameter");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
            response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Xóa nhiều parameter")
    @PostMapping("/delete-multiple")
    public ResponseEntity<ApiResponse> deleteMultiple(@RequestBody DeleteRequest request, HttpServletRequest httpRequest) {
        ApiResponse response = new ApiResponse();
        try {
            if (request.getIds() == null || request.getIds().isEmpty()) {
                response.setStatus(ApiStatus.STATUS_ERROR.getStatus());
                response.setCode(ApiStatus.STATUS_ERROR.getStatusCode());
                response.setMessage("Danh sach id khong duoc rong");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            sysParameterService.deleteMultiple(request.getIds());
            response.setCode(ApiStatus.SUCCESS.getStatusCode());
            response.setMessage("Xóa " + request.getIds().size() + " parameter thành công");
            response.setStatus(ApiStatus.SUCCESS.getStatus());
            
            userAuditLogService.saveAuditLog(httpRequest, "POST", "", request, "sys-parameter");
            
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

