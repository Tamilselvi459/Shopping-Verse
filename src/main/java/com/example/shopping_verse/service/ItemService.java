package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.ItemRequestDto;
import com.example.shopping_verse.exception.CustomerNotFoundException;
import com.example.shopping_verse.exception.InsufficientQuantityException;
import com.example.shopping_verse.exception.ProductNotFoundException;
import com.example.shopping_verse.model.Customer;
import com.example.shopping_verse.model.Item;
import com.example.shopping_verse.model.Product;
import com.example.shopping_verse.repository.CustomerRepository;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public Item createItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        if(customer == null){
            throw new CustomerNotFoundException("customer does not exists");
        }
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("product does not exists");
        }
        Product product = optionalProduct.get();

        if(itemRequestDto.getRequiredQuantity() > product.getAvailableQuantity()){
            throw new InsufficientQuantityException("Insufficient Quantity");
        }
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;



    }
}
