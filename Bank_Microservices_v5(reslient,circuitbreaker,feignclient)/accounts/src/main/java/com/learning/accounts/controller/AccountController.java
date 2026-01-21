package com.learning.accounts.controller;

import com.learning.accounts.constants.AccountsConstants;
import com.learning.accounts.dto.AccountsContactInfoDto;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.ErrorResponseDto;
import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.service.AccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(
        name = "CRUD Rest APIs for Accounts in RamBanks",
        description = "Create, Delete, update &" +
                " Fetch operations for Ram bank"
)
@RestController
@RequestMapping(path = "/v1.0/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
//Removed all constructor to use @value
@Validated// will say to do spring validation that we mention DTo class example(size,notempty etc)
public class AccountController {

    private AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountsService) {
        this.accountService = accountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

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
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @Retry(name= "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        logger.debug("getBuildInfo() method Invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback() {
        logger.debug("getBuildInfo() method Invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("0.9");
    }



    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<Map<String, String>> getJavaVersion() {
        Map<String, String> javaInfo = new HashMap<>();
        
        // Try to get JAVA_HOME from environment
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome == null || javaHome.isEmpty()) {
            // If JAVA_HOME is not set, try to get it from system properties
            javaHome = System.getProperty("java.home");
        }
        
        // Add Java version information
        javaInfo.put("java.version", System.getProperty("java.version"));
        javaInfo.put("java.vendor", System.getProperty("java.vendor"));
        javaInfo.put("java.home", javaHome);
        javaInfo.put("java.vm.version", System.getProperty("java.vm.version"));
        javaInfo.put("java.runtime.name", System.getProperty("java.runtime.name"));
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(javaInfo);
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Java 21");
    }


    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }




}
