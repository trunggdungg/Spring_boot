package com.example.lab1.Controller;

import com.example.lab1.Entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    List<Product> products = new ArrayList<>(List.of(
        new Product("1", "iPhone 12", "The latest iPhone", 999, "Apple"),
        new Product("2", "Galaxy S21", "Flagship phone from Samsung", 799, "Samsung"),
        new Product("3", "Pixel 5", "Google's latest Pixel phone", 699, "Google"),
        new Product("4", "OnePlus 8T", "Premium phone with smooth performance", 749, "OnePlus"),
        new Product("5", "Sony Xperia 5 II", "High-end phone from Sony", 899, "Sony"),
        new Product("6", "Xiaomi Mi 11", "Affordable flagship from Xiaomi", 699, "Xiaomi"),
        new Product("7", "iPhone 13", "The latest iPhone", 1999, "Apple")

    ));

    @GetMapping("/product/getAllProduct")
    public List<Product> getAllProduct() {
        return products;
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id) {
        for (Product product : products) {
            if (Objects.equals(product.getId(), id)) {
                return product;
            }
        }
        return null;
    }


    @GetMapping("/product/getProductByBrand/{brand}")
    public List<Product> getAllProductByBrand(@PathVariable String brand) {
        return products.stream().filter(product -> product.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
    }

    @GetMapping("/product/getProduct/min/{minPrice}/max/{maxPrice}")
    public List<Product> getProductsInRange(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return products.stream().filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    @GetMapping("/product/brand/{brand}/max-price")
    public Product getProductPriceMax(@PathVariable String brand) {
        Optional<Product> maxPriceProduct = products.stream().filter(product -> product.getBrand().equalsIgnoreCase(brand)).max((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()));
        return maxPriceProduct.orElse(null);
    }

    @GetMapping("/product/name-start/{prefix}")
    public List<Product> getProductsByName(@PathVariable String prefix) {
        return products.stream().filter(product -> product.getName().toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
    }
}
