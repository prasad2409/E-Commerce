package com.OnlineMarket.Ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDto {
    private String name;
    private String mobileNo;
    private String email;
}
