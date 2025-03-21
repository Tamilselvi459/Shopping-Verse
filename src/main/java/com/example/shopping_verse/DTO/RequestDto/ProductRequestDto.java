package com.example.shopping_verse.DTO.RequestDto;

import com.example.shopping_verse.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    int sellerId;
    String name;
    int availableQuantity;
    int price;
    ProductCategory productCategory;

}
