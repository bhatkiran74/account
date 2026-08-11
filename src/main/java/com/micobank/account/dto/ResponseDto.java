package com.micobank.account.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold API response status and message"
)
public class ResponseDto {

    @Schema(
            description = "HTTP status code of the response",
            example = "200",
            maxLength = 3
    )
    private String statusCode;

    @Schema(
            description = "Status message describing the result of the operation",
            example = "Request processed successfully"
    )
    private String statusMessage;
}
