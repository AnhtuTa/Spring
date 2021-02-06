package hello.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.entity.Post;
import hello.repository.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public Object getPosts() {
        return postRepository.findAll();
    }

    public Object getPost(Integer id) {
        Optional<Post> postOp = postRepository.findById(id);
        if(postOp.isPresent()) return postOp.get();
        else return "Post not found!";
    }

    public Object getPostTitles() {
        return postRepository.getPostTitles();
    }

    public Object getPostDetails() {
        return postRepository.getPostDetails();
    }

}
