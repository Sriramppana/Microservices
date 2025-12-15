package com.learning.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(description = "API Path", example = "/api/v1/accounts")
    private String apiPath;
    @Schema(description = "Error Code", example = "400")
    private HttpStatus errorCode;
    @Schema(description = "Error Message", example = "Account not found")
    private String errorMessage;
    @Schema(description = "Error Time", example = "2022-01-01T00:00:00")
    private LocalDateTime errorTime;
}
