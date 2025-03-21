package com.example.shopping_verse.controller;

import com.example.shopping_verse.DTO.RequestDto.ProductRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.ProductResponseDto;
import com.example.shopping_verse.Enum.ProductCategory;
import com.example.shopping_verse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// product

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponseDto , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
    // get all the products selling by seller using sellerId
    @GetMapping("/get-product-bySellerId")
    public ResponseEntity getProductBySeller(@RequestParam int sellerId){
        List<String> listOfProducts = productService.getProductBySeller(sellerId);
        return new ResponseEntity<>(listOfProducts , HttpStatus.OK);

    }
    // get top 3 cheapest products in a category
    @GetMapping("/get-product-cheapest")
    public ResponseEntity getCheapestProduct(@RequestParam String category){
          List<String> listOfProduct = productService.getCheapestProduct(category);
        return new ResponseEntity<>(listOfProduct, HttpStatus.OK);

    }

}

