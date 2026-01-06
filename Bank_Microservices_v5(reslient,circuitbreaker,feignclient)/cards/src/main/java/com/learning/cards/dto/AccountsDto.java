package com.learning.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts",
        description="Schema to hold Accounts information"
)
public class AccountsDto {
    @NotEmpty(message = "Account Number is required")
    @Pattern(regexp = "$|[0-9]{10}",message="Mobile Number should be 10 digits value")
   @Schema(description="Account Number of the customer",example="9234567890")
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Schema(description="Account type of the customer",example="Savings")
    private String accountType;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Schema(description="Branch Address of the customer",example="123 Main Street")
    private String branchAddress;
}
