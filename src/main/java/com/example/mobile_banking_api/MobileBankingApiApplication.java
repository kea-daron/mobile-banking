package com.example.mobile_banking_api;

import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MobileBankingApiApplication implements CommandLineRunner {

	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {

		KYC kyc = new KYC();
		Customer customer = new Customer();

		kyc.setNationalCardID("999999000");
		kyc.setIsVerified(false);
		kyc.setIsDeleted(false);
		kyc.setCustomer(customer);

		customer.setFullName("Kea Daron");
		customer.setGender("Male");
		customer.setEmail("daron3327@gmail.com");
		customer.setPhoneNumber("1234567890");
		customer.setKyc(kyc);
		customer.setRemark("INSTRUCTOR");
		customer.setIsDeleted(false);

		customerRepository.save(customer);
	}

	public static void main(String[] args) {
		SpringApplication.run(MobileBankingApiApplication.class, args);
	}

}
