package com.OnlineMarket.Ecommerce.Convertor;

import com.OnlineMarket.Ecommerce.Model.Seller;
import com.OnlineMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.SellerResponseDto;

public class SellerConvertor {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .panCard(sellerRequestDto.getPanCard())
                .mobileNo(sellerRequestDto.getMobNo())
                .build();
    }
    public static SellerResponseDto sellerToSellerResponseList(Seller seller){
        return SellerResponseDto.builder()
                .email(seller.getEmail())
                .name(seller.getName())
                .mobileNo(seller.getMobileNo()).build();
    }
}
