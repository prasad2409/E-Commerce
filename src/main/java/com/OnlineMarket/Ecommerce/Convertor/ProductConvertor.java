package com.OnlineMarket.Ecommerce.Convertor;

import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.RequestDTO.ProductRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.ProductResponseDto;

public class ProductConvertor {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity()).build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory()).build();
    }
}
