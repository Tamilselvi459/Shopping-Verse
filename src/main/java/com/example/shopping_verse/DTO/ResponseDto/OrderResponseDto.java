package com.example.shopping_verse.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

    String customerName;
    String orderId;
    Date orderDate;
    int total;
    String cardUsed;
    List<ItemResponseDto> itemResponseDtoList;

}
