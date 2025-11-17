package com.learning.accounts.respository;

import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long CustomerId);
   @Transactional
   @Modifying// whenever we modifying or updating data this 2 annonation are required
    void deleteByCustomerId(Long customerId);
}
