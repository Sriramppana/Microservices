package com.learning.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description="Schema to hold Accounts & Customers information"
)
public class CustomerDto {
    @NotEmpty(message = "Name is required")
    @Size(min=3,max=30,message="Name should be between 3 and 30 characters")
    @Schema(description="Name of the customer",example="Sriram Appan a")
    private String name;
    @NotEmpty(message="Email is required")
    @Email(message="Email Address should be valid value")
    @Schema(description="Email of the customer",example="Sriram@gmail.com")
    private String email;
    @Pattern(regexp = "$|[0-9]{10}",message="Mobile Number should be 10 digits value")
   @Schema(description="Mobile Number of the customer",example="1234567890")
    private String mobileNumber;
    @Schema(description="Accounts of the customer")
    private AccountsDto accountsDto;
}
