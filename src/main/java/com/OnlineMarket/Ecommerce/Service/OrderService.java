package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Enum.ProductStatus;
import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.Exception.ProductNotFoundException;
import com.OnlineMarket.Ecommerce.Model.*;
import com.OnlineMarket.Ecommerce.Repository.CustomerRepository;
import com.OnlineMarket.Ecommerce.Repository.ProductRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.OrderRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    JavaMailSender emailSender;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Customer Id Not Found");
        }
        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product Id is Invalid !!!");
        }
        if(product.getQuantity() <orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }
        Ordered ordered = new Ordered();
        ordered.setTotalCost(orderRequestDto.getRequiredQuantity() * product.getPrice());
        ordered.setDeliveryCharge(40);

        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++){
            cardNo += 'X';
        }
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        ordered.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(ordered);
        ordered.getItems().add(item);
        ordered.setCustomer(customer);

        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        if(leftQuantity <= 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);

        customer.getOrders().add(ordered);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getProductName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(ordered.getTotalCost())
                .deliveryCharge(40)
                .build();

        String text = "Congrats "+customer.getName() +" your order "+orderResponseDto.getProductName()+"with total value "+ordered.getTotalCost()+" has been placed";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("durgaprasad24a@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDto;
    }
}
