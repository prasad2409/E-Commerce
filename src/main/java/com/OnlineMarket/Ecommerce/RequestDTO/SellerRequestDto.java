package com.OnlineMarket.Ecommerce.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDto {
    private String name;
    private String email;
    private String mobNo;
    private String panCard;
}
