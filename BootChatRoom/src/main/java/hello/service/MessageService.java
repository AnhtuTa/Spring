package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.Message;
import hello.entity.User;
import hello.entity.Room;
import hello.repository.MessageRepository;
import hello.repository.UserRepository;
import hello.repository.RoomRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository myUserRepository;

	/**
	 * insert message vào database, rõ ràng cách này cần 3 lần truy cập vào
	 * database: 2 lần đầu để lấy các đối tượng user và room, lần 3 để save msg.
	 * @return id của message vừa được insert
	 */
	@Deprecated
	public long insertMessage_stupidWay(Message msg, long userId, long roomId) {
		System.out.println("[MessageService] start insertMessage_stupidWay");
		User user = myUserRepository.findById(userId);
		Room r = roomRepository.findById(roomId);
		
		System.out.println("[MessageService] " + r.getName());
		System.out.println("[MessageService] " + user.getFullname());

		msg.setUser(user);
		msg.setRoom(r);
		
		Message savedMsg = messageRepository.save(msg);
		return savedMsg.getId();
		
		/*
		Nếu dùng hàm này thì JPA thực hiện 3 câu SQL sau:
		Hibernate: 
		    select
		        room0_.id as id1_2_0_,
		        room0_.name as name2_2_0_ 
		    from
		        room room0_ 
		    where
		        room0_.id=?
		Hibernate: 
		    select
		        user0_.id as id1_3_0_,
		        user0_.fullname as fullname2_3_0_,
		        user0_.password as password3_3_0_,
		        user0_.username as username4_3_0_,
		        roles1_.user_id as user_id1_4_1_,
		        role2_.id as role_id2_4_1_,
		        role2_.id as id1_1_2_,
		        role2_.name as name2_1_2_ 
		    from
		        user user0_ 
		    left outer join
		        user_role roles1_ 
		            on user0_.id=roles1_.user_id 
		    left outer join
		        role role2_ 
		            on roles1_.role_id=role2_.id 
		    where
		        user0_.id=?
		Hibernate: 
		    insert 
		    into
		        message
		        (content, room_id, user_id) 
		    values
		        (?, ?, ?)
		Rõ ràng là không tối ưu tị nào!
		*/
	}
	
	public void getMessage(int start, int amount) {
		
	}
}
