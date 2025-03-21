package com.example.shopping_verse.controller;

import com.example.shopping_verse.DTO.RequestDto.CheckRequestDto;
import com.example.shopping_verse.DTO.RequestDto.ItemRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.CardResponseDto;
import com.example.shopping_verse.DTO.ResponseDto.CartResponseDto;
import com.example.shopping_verse.DTO.ResponseDto.OrderResponseDto;
import com.example.shopping_verse.model.Cart;
import com.example.shopping_verse.model.Item;
import com.example.shopping_verse.service.CartService;
import com.example.shopping_verse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add-cart")
    public ResponseEntity addTocart(@RequestBody ItemRequestDto itemRequestDto){


        try{

    Item item = itemService.createItem(itemRequestDto);
    CartResponseDto cartResponseDto = cartService.addToCart(item , itemRequestDto);
    return new ResponseEntity<>(cartResponseDto , HttpStatus.OK);

    } catch (Exception e) {
   return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
   }

    }

    @PostMapping("/place-order-fromCart")
    public ResponseEntity checkoutCart(@RequestBody CheckRequestDto checkRequestDto){

       try{
           OrderResponseDto orderResponseDto = cartService.checkoutCart(checkRequestDto);
           return new ResponseEntity<>(orderResponseDto , HttpStatus.OK);
       }catch(Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
       }
    }
}
