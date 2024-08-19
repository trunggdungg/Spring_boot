package com.example.ktra_lab.untils;

import com.example.ktra_lab.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader implements IFileReader {
    @Override
    public List<Product> read(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = new ArrayList<>();

        try {
            products = objectMapper.readValue(new File(path),
                new TypeReference<List<Product>>() {
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


}
