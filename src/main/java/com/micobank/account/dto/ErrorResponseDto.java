package com.micobank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold API error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path that was called",
            example = "/api/v1/account/fetch"
    )
    private String apiPath;

    @Schema(
            description = "HTTP error code",
            example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Detailed error message",
            example = "Customer not found with the given mobile number"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2024-01-15T10:30:00"
    )
    private LocalDateTime errorTime;
}
