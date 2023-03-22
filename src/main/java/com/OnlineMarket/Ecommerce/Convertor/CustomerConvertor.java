package com.OnlineMarket.Ecommerce.Convertor;

import com.OnlineMarket.Ecommerce.Model.Customer;
import com.OnlineMarket.Ecommerce.RequestDTO.CustomerRequestDto;

public class CustomerConvertor {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobileNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
    }
}
