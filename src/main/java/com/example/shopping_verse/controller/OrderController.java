package com.example.shopping_verse.controller;

import com.example.shopping_verse.DTO.RequestDto.OrderRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.OrderResponseDto;
import com.example.shopping_verse.model.OrderEntity;
import com.example.shopping_verse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
       try{
           OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
           return new ResponseEntity<>(orderResponseDto , HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
       }

    }


}
