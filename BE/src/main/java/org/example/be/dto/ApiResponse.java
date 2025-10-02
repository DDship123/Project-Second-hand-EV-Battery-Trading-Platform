package org.example.be.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
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

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(200)
                .message("SUCCESS")
                .payload(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> ok(T data, Map<String, Object> metadata) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(200)
                .message("SUCCESS")
                .payload(data)
                .metadata(metadata)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(int code, String message, Map<String, String> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(code)
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
