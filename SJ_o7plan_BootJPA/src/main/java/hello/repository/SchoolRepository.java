package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
	List<School> findAll();
}
