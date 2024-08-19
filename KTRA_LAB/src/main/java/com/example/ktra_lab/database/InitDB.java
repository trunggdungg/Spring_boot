package com.example.ktra_lab.database;

import com.example.ktra_lab.untils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;

@Component
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("đọc file json");
        ProductDB.products = fileReader.read("product.json");
        System.out.println("count: " + ProductDB.products.size());
    }
}
