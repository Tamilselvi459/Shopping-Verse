package com.example.shopping_verse.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCustomerResponseDto {

    String name;
    String email;
    String msg;

}