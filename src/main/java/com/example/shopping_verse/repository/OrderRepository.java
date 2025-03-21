package com.example.shopping_verse.repository;

import com.example.shopping_verse.model.Customer;
import com.example.shopping_verse.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {




}
