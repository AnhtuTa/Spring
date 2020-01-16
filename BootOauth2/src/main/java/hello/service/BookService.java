package hello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.common.ResponseStatus;
import hello.entity.Book;
import hello.exception.RestException;
import hello.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(int id) {
        Optional<Book> bookOp = bookRepository.findById(id);
        if(bookOp.isPresent()) {
            return bookOp.get();
        }
        throw new RestException(ResponseStatus.BOOK_NOT_FOUND);
    }
}
