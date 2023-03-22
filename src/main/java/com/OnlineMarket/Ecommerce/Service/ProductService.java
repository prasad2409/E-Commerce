package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.ProductConvertor;
import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import com.OnlineMarket.Ecommerce.Exception.SellerNotFoundException;
import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.Repository.ProductRepository;
import com.OnlineMarket.Ecommerce.Repository.SellerRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.ProductRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;


    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller;

        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotFoundException("Invalid SellerId");
        }
        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);

        sellerRepository.save(seller);

        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory){
        List<Product> products = productRepository.findAllByProductCategory(productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}
