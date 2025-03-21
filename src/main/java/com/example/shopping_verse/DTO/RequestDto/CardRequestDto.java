package com.example.shopping_verse.DTO.RequestDto;

import com.example.shopping_verse.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String customerEmail;
    String cardNo;
    Date validTill;
    int cvv;
    CardType cardType;


}
