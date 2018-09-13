package hello.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.entity.Gif;

@Transactional
public interface GifRepository extends JpaRepository<Gif, Integer> {
	public Gif findById(int id);
	
	public List<Gif> findByType(String type);
}
