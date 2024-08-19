package com.example.ktra_lab.untils;

import com.example.ktra_lab.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IFileReader {
    List<Product> read(String path);
}
