package com.OnlineMarket.Ecommerce.DTO;

import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import com.OnlineMarket.Ecommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private int price;

    ProductCategory productCategory;

    private int quantity;

    private int sellerId;
}
