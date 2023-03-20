package com.OnlineMarket.Ecommerce.Controller;

import com.OnlineMarket.Ecommerce.DTO.ProductCategoryRequestDto;
import com.OnlineMarket.Ecommerce.DTO.ProductRequestDto;
import com.OnlineMarket.Ecommerce.DTO.ProductResponseDto;
import com.OnlineMarket.Ecommerce.Model.Product;
import com.OnlineMarket.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }
}
