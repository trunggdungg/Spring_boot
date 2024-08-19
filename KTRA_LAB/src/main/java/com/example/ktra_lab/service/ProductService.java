package com.example.ktra_lab.service;

import com.example.ktra_lab.model.Product;

import java.util.List;

public interface ProductService {

    Product findProcuctById(int id);
    List<Product> getAllProduct();
    List<Product> findProductByName(String name);
    List<Product> findProductByPrice(int fromPrice, int toPrice);

    List<Product> sortByPrice();
}
