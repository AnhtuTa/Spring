package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value = "INSERT INTO comment(post_id, content) VALUES (:post_id, :content);", nativeQuery = true)
	public void insertComment(@Param("post_id") int post_id, @Param("content") String content);
}
