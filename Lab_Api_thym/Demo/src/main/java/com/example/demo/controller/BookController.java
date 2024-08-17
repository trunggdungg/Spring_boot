package com.example.demo.controller;


import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import com.example.demo.model.PageResponse;
import com.example.demo.model.PageResponseImPL;
import com.example.demo.service.BookService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    private List<Book> books = new ArrayList<>();

    public BookController() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Book book = Book.builder()
                .id(i)
                .title(faker.book().title())
                .author(faker.book().author())
                .year(faker.number().numberBetween(1950, 2021))
                .build();
            books.add(book);
        }
    }


    //phân trang
    // mặc định ko có page và size thì trả về trang 1 và 10 phần tử
    //currentPage = trang hiện tại là trang nào
    //pageSize = số lượng phần tử trên 1 trang là bao nhiêu
    //totalElements = tổng số phần tử
    //totalPages = tổng số trang = totalElements/pageSize
    //content = danh sách phần tử trên trang hiện tại

    @GetMapping("/books") // GET: http://localhost:8082/books?page=1&size=10
    public String getBooks(Model model,
                           @RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int size) {

        PageResponse<Book> pageResponse = PageResponseImPL.<Book>builder()
            .currentPage(page)
            .pageSize(size)
            .data(books)
            .build();
        model.addAttribute("pageResponse", pageResponse);
        return "book";
    }

// khi click submit thi vao dây
    @GetMapping
    public String getBooks(Model model, @RequestParam(required = false) String title) {
        List<Book> newbooks;
        if (title != null && !title.isEmpty()) {
            newbooks = BookDB.books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            newbooks = BookDB.books;
        }

        model.addAttribute("books", newbooks);
        return "index";
    }



















    @GetMapping("books/search/{title}")
    public String findBookByTitle(Model model, @PathVariable String title) {
        List<Book> books = bookService.findBookByTitle(title);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books/{id}")
    public String getBookDetail(Model model, @PathVariable int id) {
        Book book = BookDB.books.stream()
            .filter(b -> b.getId() == id)
            .findFirst()
            .orElse(null);
        model.addAttribute("book", book);
        return "bookDetail";
    }

    @GetMapping("/books/sortByYear")
    public String sortByYear(Model model) {
        List<Book> books = bookService.sortByYear();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public String searchByYear(Model model, @PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> books = bookService.findBookBeetweenYear(fromYear, toYear);
        model.addAttribute("books", books);
        return "index";
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
//    //lay sach theo id
//@PathVariable //dùng để lấy giá trị linh động

//    @GetMapping("/books/{id}")// GET: http:localhost:8082/books/1
//    @ResponseBody
//    public Book getBooksId(@PathVariable int id){// nên đặt trùng tên là id, nếu đt khac thì phải map name ="id"
//        Book book = bookService.findBookById(id);
//        if(book ==null){
//            return null;
//        }
//        return book;
//    }


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
//    @GetMapping("/books/sortByYear")
//    public List<Book> sortBook(){
//        // Sắp xếp danh sách sách theo năm xuất bản giảm dần
//
//        return bookService.sortByYear();
//    }
////
//        @GetMapping("/books/searchByTitle/{key}")
//    public List<Book> searchByTitle(@PathVariable String key) {
//       List<Book>  books = bookService.findBookByTitle(key);
//    return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(key)).collect(Collectors.toList());
//    }
//
////    //tìm kiêm sách theo tên
////    @GetMapping("/books/searchByTitle/{key}")
////    public List<Book> seachByTitle(@PathVariable String key){
////        return books.stream()
////            .filter(book -> book.getTitle().toLowerCase().contains(key.toLowerCase())).collect(Collectors.toList());
////    }
//
//    // tìm kiếm sách được xuất bản trong khoảng thời gian(fromYear , to year)
//    @GetMapping("/books/seachByYear/fromYear/{fromYear}/toYear/{toYear}")
//    public List<Book> seachByYear(@PathVariable int fromYear, @PathVariable int toYear){
//        List<Book> result = bookService.findBookBeetweenYear(fromYear,toYear);
//            return result;
//    }

}
