package org.example.be.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private int statusCode;
    private T payload;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
    private Map<String, Object> metadata;

    // Factory method using constructor and method chaining
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.statusCode = 200;
        response.message = "SUCCESS";
        response.payload = data;
        response.timestamp = LocalDateTime.now();
        return response;
    }

    // Overloaded method with metadata using fluent interface
    public static <T> ApiResponse<T> ok(T data, Map<String, Object> metadata) {
        return ok(data).withMetadata(metadata);
    }

    // Error method using direct field assignment
    public static <T> ApiResponse<T> error(int code, String message, Map<String, String> errors) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.statusCode = code;
        response.message = message;
        response.errors = errors;
        response.timestamp = LocalDateTime.now();
        return response;
    }

    // Helper method for fluent interface
    public ApiResponse<T> withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    // Alternative: Create factory methods with different patterns
    public static <T> ApiResponse<T> success(T data) {
        return createResponse(true, 200, "SUCCESS", data, null, null);
    }

    public static <T> ApiResponse<T> failure(int code, String message) {
        return createResponse(false, code, message, null, null, null);
    }

    public static <T> ApiResponse<T> failure(int code, String message, Map<String, String> errors) {
        return createResponse(false, code, message, null, errors, null);
    }

    // Private helper method to reduce code duplication
    private static <T> ApiResponse<T> createResponse(boolean success, int statusCode, String message,
                                                   T payload, Map<String, String> errors,
                                                   Map<String, Object> metadata) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = success;
        response.statusCode = statusCode;
        response.message = message;
        response.payload = payload;
        response.errors = errors;
        response.metadata = metadata;
        response.timestamp = LocalDateTime.now();
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
