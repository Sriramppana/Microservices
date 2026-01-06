package com.learning.accounts.service.client;

import com.learning.accounts.dto.CardsDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping("v1.0/cards/fetchCardDetails")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("ramBank-correlation-id") String correlationId, @RequestParam String mobileNumber);

}
