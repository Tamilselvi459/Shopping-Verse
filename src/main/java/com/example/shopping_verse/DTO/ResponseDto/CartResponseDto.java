package com.example.shopping_verse.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;
    int cartTotal;
    List<ItemResponseDto> itemResponseDtoList;
}
