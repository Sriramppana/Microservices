package com.learning.accounts.service.impl;

import com.learning.accounts.dto.AccountsDto;
import com.learning.accounts.dto.CardsDto;
import com.learning.accounts.dto.CustomerDetailsDto;
import com.learning.accounts.dto.LoansDto;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.exception.ResourceNotFoundException;
import com.learning.accounts.mapper.AccountMapper;
import com.learning.accounts.mapper.CustomerMapper;
import com.learning.accounts.respository.AccountsRepository;
import com.learning.accounts.respository.CustomerRepository;
import com.learning.accounts.service.CustomerDetailsService;
import com.learning.accounts.service.client.CardsFeignClient;
import com.learning.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsImpl implements CustomerDetailsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchAccountDetailsByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity=cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        ResponseEntity<LoansDto> loansDtoResponseEntity=loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        return customerDetailsDto;
    }
}
