package com.learning.accounts.controller;

import com.learning.accounts.constants.AccountsConstants;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.ErrorResponseDto;
import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest APIs for Accounts in RamBanks",
        description = "Create, Delete, update &" +
                " Fetch operations for Ram bank"
)
@RestController
@RequestMapping(path = "/v1.0/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
//THIS IS LOMBOK ANNONATION , USE FOR CONSTRUCTION INJECTION NOT AUTOWIRED TO ACCOUNT SERVICE INJECTION
@Validated// will say to do spring validation that we mention DTo class example(size,notempty etc)

public class AccountController {


    private AccountService accountService;

    @Operation(
            summary = "Creata Account Rest Api",
            description = "REST API to creare new customer & Account inside RamBanks"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Account create failed with internal server error",
            content=@Content(schema=@Schema(implementation= ErrorResponseDto.class))
    )
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
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
            content=@Content(schema=@Schema(implementation= ErrorResponseDto.class))
    )
    @GetMapping("/fetchAccountDetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "$|[0-9]{10}", message = "Mobile Number should be 10 digits value")
                                                           String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetailsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account Rest Api",
            description = "REST API to Update customer & Account inside RamBanks"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account updated successfully"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Account update failed with internal server error",
            content=@Content(schema=@Schema(implementation= ErrorResponseDto.class))
    )

    @PutMapping("/updateAccountDetails")
    public ResponseEntity<ResponseDto> updatesAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdatedFlag = accountService.updateAccount(customerDto);

        if (isUpdatedFlag) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account Rest Api",
            description = "REST API to Delete customer & Account inside RamBanks"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account deleted successfully"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Account Delete failed with internal server error",
            content=@Content(schema=@Schema(implementation= ErrorResponseDto.class))
    )
    @DeleteMapping("/deleteAccountDetails")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "$|[0-9]{10}", message = "Mobile Number should be 10 digits value")
                                                            String mobileNumber) {
        boolean isDeletedFlag = accountService.DeleteAccount(mobileNumber);

        if (isDeletedFlag) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }


}
