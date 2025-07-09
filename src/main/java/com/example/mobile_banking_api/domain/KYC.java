package com.example.mobile_banking_api.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class KYC {

    @Id
    private Integer id;
    private String nationalCardID;
    private Boolean isVerified;
    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
