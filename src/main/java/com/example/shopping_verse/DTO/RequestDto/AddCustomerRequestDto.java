package com.example.shopping_verse.DTO.RequestDto;

import com.example.shopping_verse.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCustomerRequestDto {

    String name;
    String address;
    String email;
    Gender gender;
    String mobileNo;


}
