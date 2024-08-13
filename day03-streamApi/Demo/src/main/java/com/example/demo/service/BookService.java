package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);
    List<Book> findBookByTitle(String title);

    List<Book> findBookBeetweenYear(int fromYear,int toYear);

    List<Book> getAllBook();

    List<Book> sortByYear();
}
