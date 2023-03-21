package com.OnlineMarket.Ecommerce.ResponseDTO;

import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    ProductCategory productCategory;
    private String name;
    private int price;
}
