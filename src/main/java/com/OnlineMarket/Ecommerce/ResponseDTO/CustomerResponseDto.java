package com.OnlineMarket.Ecommerce.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllCustomerResponseDto {
    private String name;
    private String email;
    private String mobNo;

}
