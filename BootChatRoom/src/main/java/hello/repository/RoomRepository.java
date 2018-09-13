package hello.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.entity.Room;

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {
	public Room findById(long id);
	
//	@Query(value="INSERT INTO user_room (user_id, room_id) VALUES (?, ?)",
//			nativeQuery=true)
//	@Modifying
//	public void insertUserToRoom(long userId, long roomId);
}
