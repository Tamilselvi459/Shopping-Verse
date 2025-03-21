package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.ResponseDto.CartResponseDto;
import com.example.shopping_verse.DTO.ResponseDto.ItemResponseDto;
import com.example.shopping_verse.model.Cart;
import com.example.shopping_verse.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCardResponseDto(Cart cart){

        List<ItemResponseDto> itemList = new ArrayList<>();
        for(Item item: cart.getItemList()){
            itemList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .itemResponseDtoList(itemList)
                .build();

    }
}
