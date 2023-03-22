package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.CustomerConvertor;
import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.Model.Card;
import com.OnlineMarket.Ecommerce.Model.Cart;
import com.OnlineMarket.Ecommerce.Model.Customer;
import com.OnlineMarket.Ecommerce.Repository.CustomerRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.AllCustomerResponseDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CardDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public String addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        customerRepository.save(customer);
        return "Congrats!! Welcome to Online Market";
    }

    public CustomerResponseDto getCustomerById(int customerId) throws CustomerNotFound {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Invalid customer id");
        }

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setMobNo(customer.getMobileNo());
        customerResponseDto.setAge(customer.getAge());

        List<CardDto> dtoList = new ArrayList<>();
        for(Card card : customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardType(card.getCardType());
            cardDto.setCardNo(card.getCardNo());
            dtoList.add(cardDto);
        }

        customerResponseDto.setCardDtoList(dtoList);

        return customerResponseDto;
    }

    public List<AllCustomerResponseDto> getAllCustomers(){

        List<Customer> customer = customerRepository.findAll();
        List<AllCustomerResponseDto> allCustomerResponseDtoList = new ArrayList<>();

        for(Customer customer1 : customer){
            AllCustomerResponseDto allCustomerResponseDto = new AllCustomerResponseDto();

            allCustomerResponseDto.setName(customer1.getName());
            allCustomerResponseDto.setEmail(customer1.getEmail());
            allCustomerResponseDto.setMobNo(customer1.getMobileNo());

            allCustomerResponseDtoList.add(allCustomerResponseDto);
        }

        return allCustomerResponseDtoList;
    }
    public String deleteCustomer(int id){
        customerRepository.deleteById(id);
        return "Customer is Deleted";
    }
}
