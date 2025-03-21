package com.example.shopping_verse.model;

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
@Table(name="seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(unique = true , nullable = false)
    String panNo;
    @Column(unique = true , nullable = false)
    String email;

    @OneToMany(mappedBy = "seller" , cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
