package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.AddCustomerRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.AddCustomerResponseDto;
import com.example.shopping_verse.model.Cart;
import com.example.shopping_verse.model.Customer;
import com.example.shopping_verse.repository.CustomerRepository;
import com.example.shopping_verse.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public AddCustomerResponseDto addCustomer(AddCustomerRequestDto addCustomerRequestDto) {

        // dto to entity
        Customer customer = CustomerTransformer.addCustomerRequestDtoToCustomer(addCustomerRequestDto);
        Cart  cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        Customer savedCustomer = customerRepository.save(customer);


       //entity to dto
        AddCustomerResponseDto addCustomerResponseDto = CustomerTransformer.customerToaddCustomerResponseDto(savedCustomer);
        return addCustomerResponseDto;


    }

    public List<String> getKorderFemaleCustomer(int k) {

        List<Customer> customerList = customerRepository.getKorderFemaleCustomer(k);
        List<String> customerNameList = new ArrayList<>();

        for(Customer customer : customerList){

            customerNameList.add(customer.getName());
        }
        return customerNameList;
    }
}
