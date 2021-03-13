package hello.repository;

import hello.entity.Post;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    @Query("SELECT p.id AS id, p.title AS title FROM Post p")
    List<Map<String, Object>> getPostTitles();

    @Query("SELECT p.id AS id, p.title AS title, p.author.name AS author, p.details.content AS content " + 
            "FROM Post p ")
    List<Map<String, Object>> getPostDetails();

}
