package com.example.shopping_verse.repository;

import com.example.shopping_verse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String customerEmail);

    @Query(value = "select c.* from customer c JOIN order_info o ON c.id = o.customer_id WHERE c.gender = 'FEMALE' GROUP By c.id HAVING COUNT(o.order_id) >= :k" , nativeQuery = true)
    List<Customer> getKorderFemaleCustomer(int k);
}
