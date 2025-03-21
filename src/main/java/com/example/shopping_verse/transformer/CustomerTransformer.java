package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.AddCustomerRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.AddCustomerResponseDto;
import com.example.shopping_verse.model.Customer;

public class CustomerTransformer {


    public static Customer addCustomerRequestDtoToCustomer(AddCustomerRequestDto addCustomerRequestDto){
        Customer customer = Customer.builder()
                           .name(addCustomerRequestDto.getName())
                           .address(addCustomerRequestDto.getAddress())
                           .email(addCustomerRequestDto.getEmail())
                           .gender(addCustomerRequestDto.getGender())
                           .mobileNo(addCustomerRequestDto.getMobileNo())
                           .build();
        return customer;
    }

    public static AddCustomerResponseDto customerToaddCustomerResponseDto(Customer savedCustomer) {

        AddCustomerResponseDto addCustomerResponseDto = AddCustomerResponseDto.builder()
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .msg("Customer saved successfully")
                .build();
        return  addCustomerResponseDto;
    }
}
