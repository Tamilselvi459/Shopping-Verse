package com.example.shopping_verse.DTO.ResponseDto;

import com.example.shopping_verse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {

    String customerName;
    CardType cardType;
    String cardNo;
    Date validTill;

}
