package com.example.mobile_banking_api.mapper;

import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.dto.CreateCustomerRequest;
import com.example.mobile_banking_api.dto.CustomerResponse;
import com.example.mobile_banking_api.dto.UpdateCustomerRequest;
import org.mapstruct.*;

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
    CustomerResponse fromCustomer(Customer customer);

    @Mapping(source = "customerSegment", target = "customerSegment.segment")
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}
