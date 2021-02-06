package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public Object getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Object getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

    @GetMapping("/title")
    public Object getPostTitles() {
        return postService.getPostTitles();
    }

    @GetMapping("/details")
    public Object getPostDetails() {
        return postService.getPostDetails();
    }
}
