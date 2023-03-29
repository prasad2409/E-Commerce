package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.ItemConvertor;
import com.OnlineMarket.Ecommerce.Exception.ProductNotFoundException;
import com.OnlineMarket.Ecommerce.Model.Item;
import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.Repository.ItemRepository;
import com.OnlineMarket.Ecommerce.Repository.ProductRepository;
import com.OnlineMarket.Ecommerce.ResponseDTO.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {
        Product product;

        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid ProductId");
        }
        Item item = ItemConvertor.productIdToItem(product);

        product.setItem(item);

        productRepository.save(product);

        ItemResponseDto itemResponseDto = ItemConvertor.productToItemResponseDtoConvertor(product);

        return itemResponseDto;
    }
}
