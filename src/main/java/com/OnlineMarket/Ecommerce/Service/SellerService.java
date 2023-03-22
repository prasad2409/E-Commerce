package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.SellerConvertor;
import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.Repository.SellerRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public String  addSeller(SellerRequestDto sellerRequestDto){
        Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);
        return "Congrats! Now you can sell on Online Market !!!";
    }
    public List<SellerResponseDto> getSellers(){

        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellers){
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseList(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }
    public List<SellerResponseDto> getSellerByPanCard(String panCard){
        List<Seller> sellers = sellerRepository.findByPanCard(panCard);
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellers){
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseList(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }
}
