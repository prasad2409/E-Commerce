package com.OnlineMarket.Ecommerce.ResponseDTO;

import com.OnlineMarket.Ecommerce.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {
    private String name;
    private String mobileNo;
    private String email;

}
