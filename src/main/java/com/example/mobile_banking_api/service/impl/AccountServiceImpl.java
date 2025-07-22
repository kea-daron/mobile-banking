package com.example.mobile_banking_api.service.impl;

import com.example.mobile_banking_api.domain.Account;
import com.example.mobile_banking_api.domain.AccountType;
import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.dto.AccountResponse;
import com.example.mobile_banking_api.dto.CreateAccountRequest;
import com.example.mobile_banking_api.dto.UpdateAccountRequest;
import com.example.mobile_banking_api.mapper.AccountMapper;
import com.example.mobile_banking_api.repository.AccountRepository;
import com.example.mobile_banking_api.repository.AccountTypeRepository;
import com.example.mobile_banking_api.repository.CustomerRepository;
import com.example.mobile_banking_api.service.AccountService;
import com.example.mobile_banking_api.util.CurrencyUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountResponse createNewAccount(CreateAccountRequest createAccountRequest) {

        // Validate balance
        switch (createAccountRequest.accountCurrency()) {
            case DOLLAR -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.TEN) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 10 dollars");
                }
            }
            case RIEL -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 40000 riels");
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Currency is not available");
        }

        Customer customer = customerRepository
                .findCustomerByPhoneNumber(createAccountRequest.customerPhoneNumber())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer phone number not found"));

        AccountType accountType = accountTypeRepository
                .findByTypeName(createAccountRequest.accountTypeName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account type not found"));

        Account account = accountMapper.toAccount(createAccountRequest);
        account.setAccountType(accountType);
        account.setCustomer(customer);

        if (account.getAccountNumber().isBlank()) { // Auto generate
            String accountNumber;
            do {
                accountNumber = String.format("%09d", new Random().nextInt(1_000_000_000)); // Max: 999,999,999
            } while (accountRepository.existsByAccountNumber(accountNumber));
            account.setAccountNumber(accountNumber);
        } else { // From DTO, check validation actNo
            if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Account number already exists");
            }
        }

        account.setIsHide(false);
        account.setIsDeleted(false);
        account.setAccountCurrency(createAccountRequest.accountCurrency().name());

        if (account.getCustomer().getCustomerSegment().getSegment().equals("REGULAR")) {
            account.setOverLimit(BigDecimal.valueOf(5000));
        } else if (account.getCustomer().getCustomerSegment().getSegment().equals("SILVER")) {
            account.setOverLimit(BigDecimal.valueOf(50000));
        } else {
            account.setOverLimit(BigDecimal.valueOf(100000));
        }

        account = accountRepository.save(account);
        return accountMapper.fromAccount(account);







//        Customer customer = customerRepository
//                .findCustomerByPhoneNumber(createAccountRequest.customerPhoneNumber())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Customer not found"));
//
//        AccountType accountType = accountTypeRepository
//                .findByTypeName(createAccountRequest.accountTypeName())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Account type not found"));
//
//        Account account = accountMapper.toAccount(createAccountRequest);
//        account.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
//        account.setIsDeleted(false);
//        account.setCustomer(customer);
//        account.setAccountType(accountType);
//
//        accountRepository.save(account);
//        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account with number " + accountNumber + " not found"));

        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findAccountsByCustomerPhoneNumber(String phoneNumber) {
        List<Account> accounts = accountRepository.findAllByCustomerPhoneNumber(phoneNumber);

        if (accounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No accounts found for customer with phone number: " + phoneNumber);
        }

        return accounts.stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account with number " + accountNumber + " not found"));

        accountRepository.delete(account);

    }

    @Override
    public AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest request) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        account.setAccountCurrency(request.accountCurrency());
        account.setBalance(request.balance());

        if (request.accountTypeName() != null) {
            AccountType accountType = accountTypeRepository
                    .findByTypeName(request.accountTypeName())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Account type not found"));
            account.setAccountType(accountType);
        }

        accountRepository.save(account);
        return accountMapper.fromAccount(account);
    }

    @Override
    public void disableAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found or already disabled"));

        account.setIsDeleted(true);
        accountRepository.save(account);
    }

}
