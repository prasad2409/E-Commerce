package com.OnlineMarket.Ecommerce.Controller;

import com.OnlineMarket.Ecommerce.DTO.SellerRequestDto;
import com.OnlineMarket.Ecommerce.DTO.SellerResponseDto;
import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity<SellerResponseDto> addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/get_sellers")
    public List<Seller> getSellers(){
        return sellerService.getSellers();
    }
}
