package com.OnlineMarket.Ecommerce.Controller;

import com.OnlineMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.SellerResponseDto;
import com.OnlineMarket.Ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }
    @GetMapping("/get_sellers")
    public List<SellerResponseDto> getSellers(){
        return sellerService.getSellers();
    }

    @GetMapping("/get/panCard")
    public List<SellerResponseDto> getSellerByPanCard(@RequestParam String panCard){
        return sellerService.getSellerByPanCard(panCard);
    }
}
