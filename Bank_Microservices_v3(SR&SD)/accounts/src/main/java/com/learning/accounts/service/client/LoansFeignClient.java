package com.learning.accounts.service.client;


import com.learning.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("v1.0/loans/fetchLoanBymobileNumber")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
