package com.example.mobile_banking_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity  // make database table
@Table(name = "Costomers") // edit name info

public class Customer {

    @Id // make primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto number of primary key
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 15, nullable = false)
    private String gender;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private KYC kyc;

    private String segment;

}
