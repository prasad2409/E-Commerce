package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.CardConvertor;
import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.Model.Card;
import com.OnlineMarket.Ecommerce.Model.Customer;
import com.OnlineMarket.Ecommerce.Repository.CardRepository;
import com.OnlineMarket.Ecommerce.Repository.CustomerRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.CardRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CardDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFound {
        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Customer id is Invalid");
        }
        Card card = CardConvertor.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1 : customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }
        cardResponseDto.setCardDto(cardDtoList);
        return cardResponseDto;
    }
}
