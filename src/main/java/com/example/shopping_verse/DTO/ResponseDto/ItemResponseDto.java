package com.example.shopping_verse.DTO.ResponseDto;

import com.example.shopping_verse.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    int quantityAdded;
    String itemName;
    int itemPrice;
    ProductCategory productCategory;
}
