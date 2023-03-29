package com.OnlineMarket.Ecommerce.Convertor;

import com.OnlineMarket.Ecommerce.Model.Item;
import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.ResponseDTO.ItemResponseDto;

public class ItemConvertor {
    public static Item productIdToItem(Product product){
        return Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();
    }
    public static ItemResponseDto productToItemResponseDtoConvertor(Product product){
        return ItemResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
