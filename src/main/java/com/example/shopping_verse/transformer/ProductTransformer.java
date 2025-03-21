package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.ProductRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.ProductResponseDto;
import com.example.shopping_verse.model.Product;

public class ProductTransformer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){

        Product product = Product.builder()
                .name(productRequestDto.getName())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .build();
        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product latestProduct) {

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .sellerName(latestProduct.getSeller().getName())
                .name(latestProduct.getName())
                .availableQuantity(latestProduct.getAvailableQuantity())
                .price(latestProduct.getPrice())
                .productCategory(latestProduct.getProductCategory())
                .productStatus(latestProduct.getProductStatus())
                .build();
        return productResponseDto;
    }
}
