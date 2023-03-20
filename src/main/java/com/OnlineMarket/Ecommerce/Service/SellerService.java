package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.DTO.SellerRequestDto;
import com.OnlineMarket.Ecommerce.DTO.SellerResponseDto;
import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){
        Seller seller = new Seller();
        seller.setEmail(sellerRequestDto.getEmail());
        seller.setName(sellerRequestDto.getName());
        seller.setPanCard(sellerRequestDto.getPanCard());
        seller.setMobileNo(sellerRequestDto.getMobNo());

        Seller addedSeller = sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setName(addedSeller.getName());
        sellerResponseDto.setEmail(addedSeller.getEmail());
        sellerResponseDto.setMobileNo(addedSeller.getMobileNo());

        return sellerResponseDto;
    }

    public List<Seller> getSellers(){
        return sellerRepository.findAll();
    }
}
