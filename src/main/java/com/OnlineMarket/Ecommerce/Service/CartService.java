package com.OnlineMarket.Ecommerce.Service;

import com.OnlineMarket.Ecommerce.Enum.ProductStatus;
import com.OnlineMarket.Ecommerce.Exception.CustomerNotFound;
import com.OnlineMarket.Ecommerce.Exception.ProductNotFoundException;
import com.OnlineMarket.Ecommerce.Model.*;
import com.OnlineMarket.Ecommerce.Repository.CustomerRepository;
import com.OnlineMarket.Ecommerce.Repository.ProductRepository;
import com.OnlineMarket.Ecommerce.RequestDTO.OrderRequestDto;
import com.OnlineMarket.Ecommerce.ResponseDTO.OrderResponseDto;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    OrderService orderService;

    public String addToCart(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Invalid Customer Id");
        }
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry !! Required quantity not available ");
        }
        Cart cart = customer.getCart();
         int newCart = cart.getCartTotal()+ orderRequestDto.getRequiredQuantity() * product.getQuantity();
         cart.setCartTotal(newCart);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to the cart!!";
    }
    public List<OrderResponseDto> checkout(int customerId) throws CustomerNotFound {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFound("Invalid Customer Id");
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        for(Item item : cart.getItems()){
            Ordered order = new Ordered();
            order.setCustomer(customer);
            order.setTotalCost(item.getRequiredQuantity() * item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.getItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo ="";
            for(int i=0;i<card.getCardNo().length()-4;i++) cardNo+='X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getProductName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDtoList.add(orderResponseDto);
        }
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        String text = "Congrats your order with total value "+totalCost+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("durgaprasad24a@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed from Online Market");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDtoList;
    }
}
