package com.example.demo.database;

import com.example.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {

    /**
     * @param args
     * @throws Exception
     */
    @Qualifier("jsonFileReader")
    @Autowired
    private IFileReader iFileReader;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file!");

      BookDB.books = iFileReader.readFile("books.json");
        System.out.println("du lieu book :" + BookDB.books.size());

    }




}
