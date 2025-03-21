package com.example.shopping_verse.DTO.ResponseDto;


import com.example.shopping_verse.Enum.ProductCategory;
import com.example.shopping_verse.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    String sellerName;

    String name;
    int availableQuantity;
    int price;

    ProductCategory productCategory;

    ProductStatus productStatus;


}
