package com.OnlineMarket.Ecommerce.Controller;

import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.CustomerCardResponseDto;
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
        CustomerCardResponseDto customerCardResponseDto;

        try {
            customerCardResponseDto = customerService.getCustomerById(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customerCardResponseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/all_customers")
    public List<CustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int customerId){
        return customerService.deleteCustomer(customerId);
    }
    @PutMapping("/update")
    public CustomerResponseDto updateCustomer(@RequestParam("id") int customerId ,@RequestParam String mobNo,@RequestParam String email) throws CustomerNotFound {
        return customerService.updateCustomer(customerId,mobNo,email);
    }
}
