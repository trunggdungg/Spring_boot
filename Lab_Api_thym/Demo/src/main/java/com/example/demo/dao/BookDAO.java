package com.example.demo.dao;

import com.example.demo.model.Book;

import java.util.List;

public interface BookDAO {

    Book findById(int id);

    List<Book> findByTitle(String title);

    List<Book> findBookByYear(int fromYear,int toYear);

    List<Book> findAll();

    List<Book> sortBookByYear();
}
