package com.example.shopping_verse.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    String cardNo;
    String emailId;
    int requiredQuantity;
    int productId;
    int cvv;
}
