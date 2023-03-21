package com.OnlineMarket.Ecommerce.RequestDTO;

import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private String name;
    private int price;

    ProductCategory productCategory;

    private int quantity;

    private int sellerId;
}
