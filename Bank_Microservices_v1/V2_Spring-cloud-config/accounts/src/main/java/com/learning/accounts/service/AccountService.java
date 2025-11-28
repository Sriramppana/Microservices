package com.learning.accounts.service;

import com.learning.accounts.dto.CustomerDto;

public interface AccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccountDetailsByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean DeleteAccount(String mobileNumber);
}
