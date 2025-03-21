package com.example.shopping_verse.repository;

import com.example.shopping_verse.Enum.ProductCategory;
import com.example.shopping_verse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findBySellerId(int sellerId);

    @Query(value = "SELECT * FROM product WHERE product_category = :category ORDER BY price ASC LIMIT 3" , nativeQuery = true)
    List<Product> getCheapestProduct(String category);
}
