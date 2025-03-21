package com.example.shopping_verse.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {

    String customerEmail;
    int requiredQuantity;
    int productId;

}
