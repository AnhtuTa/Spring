package hello.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.entity.Book;

@RestController
@RequestMapping("/api/book/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class BookAdminController {

    @Autowired
    private BookController bookController;

    @GetMapping(value = "/all")
    public List<Book> getAllBooks() {
        return bookController.getAllBooks();
    }

    @GetMapping(value = "/{id}")
    public Book getBook(@PathVariable("id") int id) {
        return bookController.getBook(id);
    }
}
