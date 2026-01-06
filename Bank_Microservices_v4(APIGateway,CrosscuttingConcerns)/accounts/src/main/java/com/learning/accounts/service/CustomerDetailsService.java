package com.learning.accounts.service;

import com.learning.accounts.dto.CustomerDetailsDto;
import com.learning.accounts.dto.CustomerDto;

public interface CustomerDetailsService {
    CustomerDetailsDto fetchAccountDetailsByMobileNumber(String mobileNumber,String correlationId);
}
