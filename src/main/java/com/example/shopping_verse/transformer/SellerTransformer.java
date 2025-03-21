package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.SellerRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.SellerResponseDto;
import com.example.shopping_verse.model.Seller;

public class SellerTransformer {

    public static Seller sellerRequestDtoToseller(SellerRequestDto sellerRequestDto){
        Seller seller = Seller.builder()
                .name(sellerRequestDto.getName())
                .panNo(sellerRequestDto.getPanNo())
                .email(sellerRequestDto.getEmail())
                .build();
        return seller;
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller) {

        SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                .email(seller.getEmail())
                .name(seller.getName())
                .build();
        return sellerResponseDto;
    }
}
