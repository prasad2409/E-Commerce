package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.DTO.ProductCategoryRequestDto;
import com.OnlineMarket.Ecommerce.DTO.ProductRequestDto;
import com.OnlineMarket.Ecommerce.DTO.ProductResponseDto;
import com.OnlineMarket.Ecommerce.Enum.ProductStatus;
import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.Repository.ProductRepository;
import com.OnlineMarket.Ecommerce.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Seller seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        Product product = new Product();
        product.setProductName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setProductCategory(productRequestDto.getProductCategory());
        product.setQuantity(productRequestDto.getQuantity());
        product.setSeller(seller);


        Product newProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductCategory(newProduct.getProductCategory());
        productResponseDto.setPrice(newProduct.getPrice());
        productResponseDto.setName(newProduct.getProductName());

        return productResponseDto;
    }

}
