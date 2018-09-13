package hello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.exception.PostNotFoundException;
import hello.model.Post;
import hello.service.PostService;

/*
 * @Cacheable — Fulfill cache after method execution, next invocation with 
 * the same arguments will be omitted and result will be loaded from cache. 
 * Annotation provide useful feature called conditional caching. In some 
 * case no all data should be cached (e.g. you want store in memory only 
 * most popular posts. Ví dụ dưới thì chỉ lưu những post có shares >= 500)
 * 
 * @CachePut — Annotation allows to update entry in cache and support same 
 * options like Cacheable annotation. Code below (updatePostByID()) updates 
 * post and return it for cache provider to change entry with new value
 * 
 * @CacheEvict — Remove entry from cache, can be both conditional and 
 * global to all entries in specific cache.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
	private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * Ví dụ: Nếu truy cập vào http://localhost:8080/posts/IDX001 thì sẽ tạo 1 cache
     * có key = post-single::IDX001
     */
    @Cacheable(value = "post-single", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable String id) throws PostNotFoundException {
        log.info("get post with id {}", id);
        return postService.getPostByID(id);
    }

    @CachePut(value = "post-single", key = "#post.id")
    @PutMapping("/update")
    public Post updatePostByID(@RequestBody Post post) throws PostNotFoundException {
        log.info("update post with id {}", post.getId());
        postService.updatePost(post);
        return post;
    }

    @CacheEvict(value = "post-single", key = "#id")
    @DeleteMapping("/delete/{id}")
    public void deletePostByID(@PathVariable String id) throws PostNotFoundException {
        log.info("delete post with id {}", id);
        postService.deletePost(id);
    }

    @Cacheable(value = "post-top")
    @GetMapping("/top")
    public List<Post> getTopPosts() {
        return postService.getTopPosts();
    }

    @CacheEvict(value = "post-top")
    @GetMapping("/top/evict")
    public void evictTopPosts() {
        log.info("Evict post-top");
    }
}
