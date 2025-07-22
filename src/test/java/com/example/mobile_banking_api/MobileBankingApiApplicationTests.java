package com.example.mobile_banking_api;

import com.example.mobile_banking_api.repository.CustomerSegmentRepository;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MobileBankingApiApplicationTests {

    @Autowired
    private CustomerSegmentRepository customerSegmentRepository;

    @Test
    public void testFindCustomerSegment() {
        customerSegmentRepository.findAll().forEach(customerSegment ->
                System.out.println(customerSegment.getCustomers()));
    }

}
