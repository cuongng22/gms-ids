package com.example.gms_ids.controller;

import com.example.gms_ids.common.ApiStatus;
import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.dto.response.ApiResponse;
import com.example.gms_ids.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Quản lý người dùng trong hệ thống")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userService.findAll(page, size));
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

    @Operation(summary = "Tạo user mới")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody UserRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(userService.create(request));
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
}
