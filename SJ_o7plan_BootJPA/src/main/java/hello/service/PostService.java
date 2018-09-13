package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.Post;
import hello.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
	public Post getPostById(int id) {
		return postRepository.findById(id).get();
	}
	
	public int savePost(Post p) {
		return postRepository.save(p).getId();
	}
}
