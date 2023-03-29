package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Convertor.CustomerConvertor;
import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.Model.Card;
import com.OnlineMarket.Ecommerce.Model.Cart;
import com.OnlineMarket.Ecommerce.Model.Customer;
import com.OnlineMarket.Ecommerce.Repository.CustomerRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CardDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerCardResponseDto;
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

    public CustomerCardResponseDto getCustomerById(int customerId) throws CustomerNotFound {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Invalid customer id");
        }

        CustomerCardResponseDto customerCardResponseDto = new CustomerCardResponseDto();
        customerCardResponseDto.setName(customer.getName());
        customerCardResponseDto.setEmail(customer.getEmail());
        customerCardResponseDto.setMobNo(customer.getMobileNo());
        customerCardResponseDto.setAge(customer.getAge());

        List<CardDto> dtoList = new ArrayList<>();
        for(Card card : customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardType(card.getCardType());
            cardDto.setCardNo(card.getCardNo());
            dtoList.add(cardDto);
        }

        customerCardResponseDto.setCardDtoList(dtoList);

        return customerCardResponseDto;
    }

    public List<CustomerResponseDto> getAllCustomers(){

        List<Customer> customer = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for(Customer customer1 : customer){
            CustomerResponseDto customerResponseDto = new CustomerResponseDto();

            customerResponseDto.setName(customer1.getName());
            customerResponseDto.setEmail(customer1.getEmail());
            customerResponseDto.setMobNo(customer1.getMobileNo());

            customerResponseDtoList.add(customerResponseDto);
        }

        return customerResponseDtoList;
    }
    public String deleteCustomer(int id){
        customerRepository.deleteById(id);
        return "Customer is Deleted";
    }
    public CustomerResponseDto updateCustomer(int id,String mobNo,String email) throws CustomerNotFound {
        Customer customer;
        try {
            customer = customerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Invalid customer Id");
        }
        customer.setMobileNo(mobNo);
        customer.setEmail(email);
        Customer updatedCustomer = customerRepository.save(customer);
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(updatedCustomer.getName());
        customerResponseDto.setEmail(updatedCustomer.getEmail());
        customerResponseDto.setMobNo(updatedCustomer.getMobileNo());

        return customerResponseDto;
    }
}
