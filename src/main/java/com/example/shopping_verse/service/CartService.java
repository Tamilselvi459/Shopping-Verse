package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.CheckRequestDto;
import com.example.shopping_verse.DTO.RequestDto.ItemRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.CartResponseDto;
import com.example.shopping_verse.DTO.ResponseDto.OrderResponseDto;
import com.example.shopping_verse.exception.CardNotValidException;
import com.example.shopping_verse.exception.CartEmptyException;
import com.example.shopping_verse.exception.CustomerNotFoundException;
import com.example.shopping_verse.model.*;
import com.example.shopping_verse.repository.*;
import com.example.shopping_verse.transformer.CartTransformer;
import com.example.shopping_verse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + (itemRequestDto.getRequiredQuantity() * product.getPrice()));
        cart.setCustomer(customer);

        item.setCart(cart);
        item.setProduct(product);
        Item savedItem = itemRepository.save(item);

        cart.getItemList().add(savedItem);
        product.getItemList().add(savedItem);

        Cart savedCart = cartRepository.save(cart);
        Product savedProduct = productRepository.save(product);

        CartResponseDto cartResponseDto = CartTransformer.CartToCardResponseDto(savedCart);
        return cartResponseDto;


    }

    public OrderResponseDto checkoutCart(CheckRequestDto checkRequestDto) {

        Customer customer = customerRepository.findByEmail(checkRequestDto.getCustomerEmail());
        if (customer == null) {
            throw new CustomerNotFoundException("customer does not exists");
        }

        Card card = cardRepository.findByCardNo(checkRequestDto.getCardNo());
        Date todayDate = new Date();
        if (card == null || card.getCvv() != checkRequestDto.getCvv() ||
                todayDate.after(card.getValidTill())) {

            throw new CardNotValidException("Invalid card");
        }

        Cart cart = customer.getCart();
        if (cart.getItemList().size() == 0) {
            throw new CartEmptyException("your cart is empty");
        }

        OrderEntity order = orderService.placeOrder(cart, card);
        resetCart(cart);
        OrderEntity savedOrder = orderRepository.save(order);
        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(savedOrder);
        return orderResponseDto;
    }

    public void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for (Item item : cart.getItemList()) {
            item.setCart(null);
        }
        cart.setItemList(new ArrayList<>());
    }
}
