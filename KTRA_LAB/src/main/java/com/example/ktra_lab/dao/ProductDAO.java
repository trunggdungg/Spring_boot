package com.example.ktra_lab.dao;

import com.example.ktra_lab.model.Product;

import java.util.List;

public interface ProductDAO {
    Product findById(int id);
    List<Product> getAll();
    List<Product> findByName(String name);
    List<Product> findByPrice(int fromPrice, int toPrice);

    List<Product> sortByPrice();
}
