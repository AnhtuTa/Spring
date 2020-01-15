package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.entity.Book;
import hello.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

}
