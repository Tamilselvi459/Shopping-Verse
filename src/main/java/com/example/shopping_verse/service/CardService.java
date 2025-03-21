package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.CardRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.CardResponseDto;
import com.example.shopping_verse.exception.CustomerNotFoundException;
import com.example.shopping_verse.model.Card;
import com.example.shopping_verse.model.Customer;
import com.example.shopping_verse.repository.CardRepository;
import com.example.shopping_verse.repository.CustomerRepository;
import com.example.shopping_verse.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        Customer customer = customerRepository.findByEmail(cardRequestDto.getCustomerEmail());
        if(customer == null){
            throw new CustomerNotFoundException("Email does not exists");
        }
        // dto to entity
        Card card  = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
         Customer savedCustomer = customerRepository.save(customer);
         List<Card> list = savedCustomer.getCardList();
         Card latestcard = list.get(list.size() - 1);

         //entity to dto
        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(latestcard);
        cardResponseDto.setCardNo(generateMask(latestcard.getCardNo()));
        return cardResponseDto;


    }
    public static String generateMask(String cardNo){
        int cardLength = cardNo.length();
        String maskedCard = "";
        for(int i=0;i<cardLength-4;i++) {
            maskedCard = maskedCard + 'x';
        }
        maskedCard += cardNo.substring(cardLength-4);
        return maskedCard;


    }
}
