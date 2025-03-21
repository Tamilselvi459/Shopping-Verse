package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.OrderRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.OrderResponseDto;
import com.example.shopping_verse.Enum.ProductStatus;
import com.example.shopping_verse.exception.CardNotValidException;
import com.example.shopping_verse.exception.CustomerNotFoundException;
import com.example.shopping_verse.exception.InsufficientQuantityException;
import com.example.shopping_verse.exception.ProductNotFoundException;
import com.example.shopping_verse.model.*;
import com.example.shopping_verse.repository.CardRepository;
import com.example.shopping_verse.repository.CustomerRepository;
import com.example.shopping_verse.repository.OrderRepository;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.transformer.ItemTransformer;
import com.example.shopping_verse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    JavaMailSender javaMailSender;


    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmail(orderRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("customer does not exists");
        }
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("product does not exists");
        }
        Product product = optionalProduct.get();

        if(orderRequestDto.getRequiredQuantity()> product.getAvailableQuantity()){
            throw new InsufficientQuantityException("Insufficient quantity");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date todayDate = new Date();
        if(card == null || card.getCvv() != orderRequestDto.getCvv() ||
                todayDate.after(card.getValidTill())){

            throw new CardNotValidException("Invalid card");
        }
        int newQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        System.out.println("quantity :" + product.getAvailableQuantity());
        if(newQuantity == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(CardService.generateMask(orderRequestDto.getCardNo()));
        order.setTotal(product.getPrice() * orderRequestDto.getRequiredQuantity());
        order.setCustomer(customer);

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(order);
        item.setProduct(product);

        order.getItemList().add(item);



        OrderEntity savedOrder = orderRepository.save(order);
        SendEmail(savedOrder);
        customer.getOrderList().add(savedOrder);
        product.getItemList().add(savedOrder.getItemList().get(0));


 //       Customer savedCustomer = customerRepository.save(customer);
//        Product savedProduct = productRepository.save(product);

        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(savedOrder);
        return orderResponseDto;



    }



    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(CardService.generateMask(card.getCardNo()));

        int orderTotal = 0;
        for(Item item : cart.getItemList()){
            Product product = item.getProduct();
            if(product.getAvailableQuantity() < item.getRequiredQuantity())
            {
                throw new InsufficientQuantityException("sorry! Insufficient quantity available for : " + product.getName());

            }
            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            orderTotal += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(order);
        }
        order.setTotal(orderTotal);
        order.setItemList(cart.getItemList());
        order.setCustomer(card.getCustomer());
        SendEmail(order);
        return order;
    }

    private void SendEmail(OrderEntity order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("projectwork243@gmail.com");
        message.setTo(order.getCustomer().getEmail());
        message.setSubject("ORDER PLACED");


        String text = "congrats your order has been placed. Following are the details \n" +"order id : " +order.getOrderId() + "\n" + "order total : "+ order.getTotal()+  "\n" + "orderDate : "
 + order.getOrderDate();

        message.setText(text);
        javaMailSender.send(message);
    }


}
