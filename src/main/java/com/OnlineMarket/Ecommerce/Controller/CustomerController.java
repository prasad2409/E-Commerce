package com.OnlineMarket.Ecommerce.Controller;

import com.OnlineMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.AllCustomerResponseDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import com.OnlineMarket.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") int customerId){
        CustomerResponseDto customerResponseDto;

        try {
            customerResponseDto = customerService.getCustomerById(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customerResponseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/all_customers")
    public List<AllCustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int customerId){
        return customerService.deleteCustomer(customerId);
    }
}
