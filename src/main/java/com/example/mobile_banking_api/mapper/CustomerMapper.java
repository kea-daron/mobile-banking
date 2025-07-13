package com.example.mobile_banking_api.mapper;

import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.dto.CreateCustomerRequest;
import com.example.mobile_banking_api.dto.CustomerRespose;
import com.example.mobile_banking_api.dto.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {



    // Partially
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest,
                             @MappingTarget Customer customer);

    // DTO -> Model
    // Model -> DTO
    // return type is converted | target data
    // parameter is source data
    CustomerRespose fromCustomer(Customer customer);

    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}
