package hello.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.entity.Author;
import hello.repository.AuthorRepository;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    public Object getAllAuthors() {
        return authorRepository.findAll();
    }

    public Object getAuthor(Integer id) {
        Optional<Author> authorOp = authorRepository.findById(id);
        if(authorOp.isPresent()) return authorOp.get();
        else return "Author not found!";
    }

}
