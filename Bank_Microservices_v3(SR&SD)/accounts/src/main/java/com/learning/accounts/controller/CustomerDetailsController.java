package com.learning.accounts.controller;


import com.learning.accounts.dto.CustomerDetailsDto;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.ErrorResponseDto;
import com.learning.accounts.service.CustomerDetailsService;
import com.learning.accounts.service.impl.CustomerDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD Rest APIs for Accounts in RamBanks",
        description = "Create, Delete, update &" +
                " Fetch operations for Ram bank"
)
@RestController
@RequestMapping(path = "/v1.0/customerDetails", produces = {MediaType.APPLICATION_JSON_VALUE})
//Removed all constructor to use @value
@Validated
public class CustomerDetailsController {

    private CustomerDetailsService customerDetailsService;

    public CustomerDetailsController(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }


    @Operation(
            summary = "Fetch Account Rest Api",
            description = "REST API to Fetch customer & Account inside RamBanks"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Account fetched successfully"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Account fetch failed with internal server error",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchAccountDetails(@RequestParam
                                                                  @Pattern(regexp = "$|[0-9]{10}", message = "Mobile Number should be 10 digits value")
                                                                  String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = customerDetailsService.fetchAccountDetailsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDetailsDto);

    }
}
