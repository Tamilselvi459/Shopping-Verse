package com.example.shopping_verse.model;

import com.example.shopping_verse.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;
    @Column(unique = true , nullable = false)
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(unique = true,nullable = false)
    String mobileNo;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<OrderEntity> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();


}
