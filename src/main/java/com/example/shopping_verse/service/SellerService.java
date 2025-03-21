package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.SellerRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.SellerResponseDto;
import com.example.shopping_verse.model.Seller;
import com.example.shopping_verse.repository.SellerRepository;
import com.example.shopping_verse.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // dto to entity
        Seller seller = SellerTransformer.sellerRequestDtoToseller(sellerRequestDto);
        Seller savedseller = sellerRepository.save(seller);
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerToSellerResponseDto(savedseller);
        return sellerResponseDto;


    }

}
