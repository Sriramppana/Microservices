package com.learning.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Loans",
        description = "Schema to hold Loan information"
)
@Data
public class LoansDto {
    @Schema(description = "Mobile Number can not a null or empty", required = true, example = "123456789")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile Number is required")
    private String mobileNumber;

    @Schema(description = "Loan Number", required = true, example = "123456789")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @NotEmpty(message = "Loan Number is required")
    private String loanNumber;

    @Schema(description = "loanType can not a null or empty", required = true)
    @NotEmpty(message = "Loan Type is required")
    private String loanType;

    @Schema(description = "Total loan amount should be greater than zero", required = true)
    @Positive(message = "Total loan amount should be greater than zero")
    private int totalLoan;


    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    @PositiveOrZero(message = "Amount paid should be greater than or equal to zero")
    private int amountPaid;

    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    @PositiveOrZero(message = "Outstanding amount should be greater than or equal to zero")
    private int outstandingAmount;

}
