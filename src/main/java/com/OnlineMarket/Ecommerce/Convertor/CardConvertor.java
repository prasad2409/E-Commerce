package com.OnlineMarket.Ecommerce.Convertor;

import com.OnlineMarket.Ecommerce.Model.Card;
import com.OnlineMarket.Ecommerce.RequestDTO.CardRequestDto;

public class CardConvertor {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
}
