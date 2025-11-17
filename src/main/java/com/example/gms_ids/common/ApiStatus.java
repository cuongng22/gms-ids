package com.example.gms_ids.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiStatus {
    STATUS_ERROR(0, "ERROR", false),
    // ====== SUCCESS ======
    SUCCESS(200, "Success", true),
    CREATED(201, "Created successfully", true),
    UPDATED(200, "Updated successfully", true),
    DELETED(200, "Deleted successfully", true),

    // ====== CLIENT ERRORS ======
    BAD_REQUEST(400, "Invalid request", false),
    UNAUTHORIZED(401, "Unauthorized", false),
    FORBIDDEN(403, "Forbidden", false),
    NOT_FOUND(404, "Resource not found", false),
    VALIDATION_ERROR(422, "Validation error", false),

    // ====== SERVER ERRORS ======
    INTERNAL_ERROR(500, "Internal server error", false),
    SERVICE_UNAVAILABLE(503, "Service unavailable", false);
    private final int statusCode;
    private final String message;
    private final Boolean status;  // SUCCESS | ERROR
}
