package com.example.mobile_banking_api.init;

import com.example.mobile_banking_api.domain.AccountType;
import com.example.mobile_banking_api.repository.AccountTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountTypeInitialize {

    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init() {
        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setTypeName("PAYROLL");

            AccountType saving = new AccountType();
            saving.setTypeName("SAVING");

            AccountType junior = new AccountType();
            junior.setTypeName("JUNIOR");

            accountTypeRepository.saveAll(List.of(payroll, saving, junior));
        }
    }

}
