package com.OnlineMarket.Ecommerce.RequestDTO;

import com.OnlineMarket.Ecommerce.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDto {

    private String cardNo;
    private int cvv;

    CardType cardType;
    private int customerId;
}
