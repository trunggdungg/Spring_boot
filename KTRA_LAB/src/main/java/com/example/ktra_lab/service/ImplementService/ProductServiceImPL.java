package com.example.ktra_lab.service.ImplementService;

import com.example.ktra_lab.dao.ProductDAO;
import com.example.ktra_lab.model.Product;
import com.example.ktra_lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImPL implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public Product findProcuctById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAll();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productDAO.findByName(name);
    }

    @Override
    public List<Product> findProductByPrice(int fromPrice, int toPrice) {
        return productDAO.findByPrice(fromPrice, toPrice);
    }

    @Override
    public List<Product> sortByPrice() {
        return productDAO.sortByPrice();
    }
}
