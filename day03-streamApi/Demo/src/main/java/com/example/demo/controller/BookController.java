package com.example.demo.controller;


import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//@Controller là nơi tiếp nhận các request từ client , xử lý dữ liệu trả v response
// controller có 2 dạng ,
//@Controller : dạng 1 là các controller trả về giao diện, ngoài ra còn có thể trả về dạng dữ liệu JSON,XML,..
//@RestController : dạng 2 là các controller trả về dũ liệu dạng json,xlm,...
// @ResController  = @Controller + @ResponseBody
//@ResponseBody : chỉ trả về dữ liệu , không trả về template, dữ liệu trả về có thẻ là jsson, xlm,..
//@ResponseEntity<?> : class đại diện cho 1  đối tượng response
//@Controller


@RestController
public class BookController {
    @Autowired
    private BookService bookService;
//
//    // lay all sach
//
    @GetMapping("/books")// GET: http:localhost:8082/books
   // @ResponseBody// dữ liệu trả về bo dy của respnonse
  //  @ResponseStatus(HttpStatus.CREATED) //201
    public List<Book> getBooks(){
        return bookService.getAllBook();
    }
//
//        @GetMapping("/books")// GET: http:localhost:8082/books
//    public ResponseEntity<List<Book>> getBooks(){
//        return new ResponseEntity<>(books,HttpStatus.CREATED);
//    }
//    //trong trường hợp chưa biết trả về dữ liệu kiểu gì thì ghi là ReponseEntity<?>
//
//
//
//
//
//    //lay sach theo id
//@PathVariable //dùng để lấy giá trị linh động
    @GetMapping("/books/{id}")// GET: http:localhost:8082/books/1
    @ResponseBody
    public Book getBooksId(@PathVariable int id){// nên đặt trùng tên là id, nếu đt khac thì phải map name ="id"
        Book book = bookService.findBookById(id);
        if(book ==null){
            return null;
        }
        return book;
    }
//
//
//    @GetMapping("/books/{id}")// GET: http:localhost:8082/books/1
//    public ResponseEntity<Book> getBooksId(@PathVariable int id){// nên đặt trùng tên là id, nếu đt khac thì phải map name ="id"
//        System.out.println("id" +id);
//        for (Book book: books) {
//            if (book.getId()==id){
//                return new ResponseEntity<>(book,HttpStatus.OK);//200
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
//    }
//
//
//    // viết api sắp xếp theo năm giảm dần
//
    @GetMapping("/books/sortByYear")
    public List<Book> sortBook(){
        // Sắp xếp danh sách sách theo năm xuất bản giảm dần

        return bookService.sortByYear();
    }
//
        @GetMapping("/books/searchByTitle/{key}")
    public List<Book> searchByTitle(@PathVariable String key) {
       List<Book>  books = bookService.findBookByTitle(key);
    return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(key)).collect(Collectors.toList());
    }
//
////    //tìm kiêm sách theo tên
////    @GetMapping("/books/searchByTitle/{key}")
////    public List<Book> seachByTitle(@PathVariable String key){
////        return books.stream()
////            .filter(book -> book.getTitle().toLowerCase().contains(key.toLowerCase())).collect(Collectors.toList());
////    }
//
//    // tìm kiếm sách được xuất bản trong khoảng thời gian(fromYear , to year)
    @GetMapping("/books/seachByYear/fromYear/{fromYear}/toYear/{toYear}")
    public List<Book> seachByYear(@PathVariable int fromYear, @PathVariable int toYear){
        List<Book> result = bookService.findBookBeetweenYear(fromYear,toYear);
            return result;
    }

}
