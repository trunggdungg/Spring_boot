package com.example.demo.dao.ImplementDAO;

import com.example.demo.dao.BookDAO;
import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImplement implements BookDAO {
    /**
     * @param id
     * @return
     */

    // thao tác với cơ sở dữ liệu là BookDB để lấy dữ liệu
    @Override
    public Book findById(int id) { // tương tự nhu select * trong sql , vì chưa có csdl nên làm như vậy
        for (Book book: BookDB.books
             ) {
            if (book.getId()== id){
                return book;
            }
        }
        return null;
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<Book> findByTitle(String title) {
        return BookDB.books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title.toLowerCase())).collect(Collectors.toList());
    }

    /**
     * @param fromYear
     * @param toYear
     * @return
     */
    @Override
    public List<Book> findBookByYear(int fromYear, int toYear) {
        return BookDB.books.stream().filter(book -> book.getYear() >= fromYear && book.getYear() <= toYear).collect(Collectors.toList());
    }

    /**
     * @param
     * @return
     */
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    /**
     * @param Year
     * @return
     */
    @Override
    public List<Book> sortBookByYear() {
      BookDB.books.sort((b1, b2) -> Integer.compare(b2.getYear(), b1.getYear()));
     return BookDB.books;

    }
}
