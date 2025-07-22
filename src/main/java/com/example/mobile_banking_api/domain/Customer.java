package com.example.mobile_banking_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity  // make database table
@Table(name = "Customers") // edit name info

public class Customer {

    @Id // make primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto number of primary key
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 15, nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String cityOrProvince;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String zipCode;

    @Column(length = 50)
    private String employmentType;

    @Column(length = 50)
    private String position;

    @Column(length = 50)
    private String companyName;

    @Column(length = 50)
    private String mainSourceOfIncome;

    @Column(length = 50)
    private String monthlyIncomeRange;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KYC kyc;

    @ManyToOne
    private CustomerSegment customerSegment;

}
