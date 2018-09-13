package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hello.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);

	public User findById(long id);
	
	// Demo select 1 column and join table
	@Query(value="SELECT u.fullname FROM user_room ur, user u "
			+ "WHERE ur.user_id = u.id AND ur.room_id = ?;", nativeQuery=true)
	public List<String> getUserFullnameInRoom(long roomId);
	
	// Demo select 2 columns
	@Query(value="SELECT u.username, u.fullname FROM user u WHERE id = ?;", 
			nativeQuery=true)
	public List<Object[]> getUsernameAndFullname(long userId);
	
	// CHÚ Ý: Có từ new: "SELECT new"
	// trả về kiểu List<User[]> cũng được!
	@Query(value="SELECT new User(u.username, u.fullname) FROM User u WHERE u.id = ?1")
	public User getUsernameAndFullname_JPQL (long userId);
	
	@Query(value="SELECT u.username, u.fullname FROM user u "
			+ "ORDER BY id DESC LIMIT 0,5;", nativeQuery=true)
	public List<Object[]> get5NewestUsernameAndFullname();
	
	@Query(value="SELECT u.fullname, m.content, m.time, r.name "
			+ "FROM message m, user u, room r "
			+ "WHERE m.user_id = u.id AND m.room_id = r.id "
			+ "AND m.room_id = 123 ORDER BY m.time DESC LIMIT 20", nativeQuery=true)
	public List<Object[]> getCommentsOfUserInRoom(long roomId);
}
