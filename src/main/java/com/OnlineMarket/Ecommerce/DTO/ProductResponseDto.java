package com.OnlineMarket.Ecommerce.DTO;

import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    ProductCategory productCategory;
    private String name;
    private int price;
}
