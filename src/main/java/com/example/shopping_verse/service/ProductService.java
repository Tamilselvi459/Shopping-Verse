package com.example.shopping_verse.service;

import com.example.shopping_verse.DTO.RequestDto.ProductRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.ProductResponseDto;
import com.example.shopping_verse.Enum.ProductCategory;
import com.example.shopping_verse.Enum.ProductStatus;
import com.example.shopping_verse.exception.SellerNotFoundException;
import com.example.shopping_verse.model.Product;
import com.example.shopping_verse.model.Seller;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.repository.SellerRepository;
import com.example.shopping_verse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Optional<Seller> optionalSeller = sellerRepository.findById(productRequestDto.getSellerId());
        if (optionalSeller.isEmpty()) {
            throw new SellerNotFoundException("Seller Not Found");
        }

        Seller seller = optionalSeller.get();

        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setProductStatus(ProductStatus.AVALIABLE);
        product.setSeller(seller);

        seller.getProductList().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        List<Product> list = savedSeller.getProductList();
        Product latestProduct = list.get(list.size() - 1);

        ProductResponseDto productResponseDto = ProductTransformer.productToProductResponseDto(latestProduct);
        return productResponseDto;
    }

    public List<String> getProductBySeller(int sellerId) {

        List<Product> listOfProducts = productRepository.findBySellerId(sellerId);
        List<String> nameOfProduct = new ArrayList<>();
        for (Product product : listOfProducts) {
            nameOfProduct.add(product.getName());
        }
        return nameOfProduct;
    }

    public List<String> getCheapestProduct(String category) {
        List<Product> listOfProduct = productRepository.getCheapestProduct(category);
        List<String> nameOfProduct = new ArrayList<>();
        for (Product product : listOfProduct) {
            nameOfProduct.add(product.getName());
        }
        return nameOfProduct;

    }
}