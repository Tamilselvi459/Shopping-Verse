package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.OrderRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.ItemResponseDto;
import com.example.shopping_verse.DTO.ResponseDto.OrderResponseDto;
import com.example.shopping_verse.model.Item;
import com.example.shopping_verse.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {



    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity savedOrder) {

        List<ItemResponseDto> itemList = new ArrayList<>();
        for(Item item: savedOrder.getItemList()){
            itemList.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .customerName(savedOrder.getCustomer().getName())
                .orderId(savedOrder.getOrderId())
                .orderDate(savedOrder.getOrderDate())
                .total(savedOrder.getTotal())
                .cardUsed(savedOrder.getCardUsed())
                .itemResponseDtoList(itemList)
                .build();

        return orderResponseDto;
    }
}
