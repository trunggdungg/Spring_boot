package com.example.ktra_lab.dao.ImplementDAO;

import com.example.ktra_lab.dao.ProductDAO;
import com.example.ktra_lab.database.ProductDB;
import com.example.ktra_lab.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImPL implements ProductDAO {
    @Override
    public Product findById(int id) {
      for (Product product : ProductDB.products){
          if (product.getId() == id){
              return product;
          }
      }
      return null;
    }

    @Override
    public List<Product> getAll() {
        return ProductDB.products;
    }

    @Override
    public List<Product> findByName(String name) {
        return ProductDB.products.stream()
            .filter(product -> product.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByPrice(int fromPrice, int toPrice) {
        return ProductDB.products.stream()
            .filter(product -> product.getPrice() >= fromPrice && product.getPrice() <= toPrice).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPrice() {
        return ProductDB.products.stream()
            .sorted((p1, p2) -> p1.getPrice() - p2.getPrice()).collect(Collectors.toList());
    }
}
