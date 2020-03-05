package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

}
