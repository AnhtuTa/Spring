package hello.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.entity.Room;
import hello.entity.User;

@Transactional
@Repository
public class RoomDAO {

	@Autowired
	private EntityManager entityManager;
	
	/**
	 * Hàm này ko tối ưu, vì phải lấy 2 thằng user và room ra từ database 
	 * rồi lại insert vào database. Nhưng làm cách này để minh họa việc:
	 * Đối tượng roomSet được quản lý bởi session, nếu thay đổi giá trị
	 * của nó thì sau khi chạy hết method, các giá trị này tự động update
	 * xuống database (do class này đã có @Transactional)
	 * @param userId
	 * @param roomId
	 */
	public void insertUserToRoom(long userId, long roomId) {
		User user = entityManager.find(User.class, userId);
		Room room = entityManager.find(Room.class, roomId);
		
		Set<Room> roomSet = user.getRooms();
		// tự động update xuống database, vì đối tượng roomSet đang ở
		// trạng thái persistant, do entityManager quản lý
		roomSet.add(room);
	}
	
	public boolean insertUserToPublicRoom(long userId, long roomId) {
		Room room = entityManager.find(Room.class, roomId);
		if(!room.getPrivacy().equals(Room.PRIVACY_PUBLIC)) return false;
		
		User user = entityManager.find(User.class, userId);
		
		Set<Room> roomSet = user.getRooms();
		// tự động update xuống database, vì đối tượng roomSet đang ở
		// trạng thái persistant, do entityManager quản lý
		roomSet.add(room);
		return true;
	}
}
