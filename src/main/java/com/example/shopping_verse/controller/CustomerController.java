package com.example.shopping_verse.controller;

import com.example.shopping_verse.DTO.RequestDto.AddCustomerRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.AddCustomerResponseDto;
import com.example.shopping_verse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody AddCustomerRequestDto addCustomerRequestDto){
        AddCustomerResponseDto addCustomerResponseDto = customerService.addCustomer(addCustomerRequestDto);
        return new ResponseEntity(addCustomerResponseDto , HttpStatus.CREATED);
    }

    // get all female customers who have orderes atleat K orders
    @GetMapping("get-k-orders-FemaleCustomer")
    public ResponseEntity getKorderFemaleCustomer(@RequestParam int k){

        List<String> customerNameList = customerService.getKorderFemaleCustomer(k);
        return new ResponseEntity(customerNameList,HttpStatus.OK);
    }
}
