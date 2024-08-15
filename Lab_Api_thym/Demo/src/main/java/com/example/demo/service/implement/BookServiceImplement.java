package com.example.demo.service.implement;

import com.example.demo.dao.BookDAO;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImplement implements BookService {
    @Autowired // dùng autowired thay vì khởi tạo đối tượng vì là bean nên chỉ cần khai báo autowired
    /**
     * @param id
     * @return
     */

    private BookDAO bookDAO;
    @Override
    public Book findBookById(int id) {
        return bookDAO.findById(id);

    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<Book> findBookByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    /**
     * @param fromYear
     * @param toYear
     * @return
     */
    @Override
    public List<Book> findBookBeetweenYear(int fromYear, int toYear) {

       // List<Book> books= bookDAO.findAll();

        return bookDAO.findBookByYear(fromYear,toYear);
    }


    /**
     * @param
     * @return
     */
    @Override
    public List<Book> getAllBook() {
        return bookDAO.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<Book> sortByYear() {
        return bookDAO.sortBookByYear();
    }


}
