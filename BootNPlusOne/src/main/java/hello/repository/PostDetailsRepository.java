package hello.repository;

import hello.entity.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Integer>, JpaSpecificationExecutor<PostDetails> {

}