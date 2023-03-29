package com.OnlineMarket.Ecommerce.ResponseDTO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCardResponseDto {

    private String name;
    private String mobNo;
    private String email;
    private int age;
    List<CardDto> cardDtoList;
}
