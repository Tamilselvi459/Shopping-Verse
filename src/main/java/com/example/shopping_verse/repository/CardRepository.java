package com.example.shopping_verse.repository;

import com.example.shopping_verse.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Card findByCardNo(String cardNo);
}
