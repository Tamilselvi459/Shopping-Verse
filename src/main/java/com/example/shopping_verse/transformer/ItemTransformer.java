package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.ItemRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.ItemResponseDto;
import com.example.shopping_verse.model.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(int requiredQuantity){

        Item item = Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();

        return item;
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){

        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .itemName(item.getProduct().getName())
                .itemPrice(item.getProduct().getPrice())
                .quantityAdded(item.getRequiredQuantity())
                .productCategory(item.getProduct().getProductCategory())
                .build();

        return itemResponseDto;

    }
}
