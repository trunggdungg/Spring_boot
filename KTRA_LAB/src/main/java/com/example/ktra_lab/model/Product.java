package com.example.ktra_lab.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    int id;
    String name;
    String description;
    String thumbnail;
    int price;
    double rating;
    int priceDiscount;
}
