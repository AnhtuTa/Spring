package hello.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hello.entity.Message;
import hello.entity.Room;
import hello.entity.User;

/*
 * Nếu không có @Transactional thì chẳng hạn khi gọi hàm insertMessage(), sẽ bị lỗi: 
 * javax.persistence.TransactionRequiredException: No EntityManager with actual 
 * transaction available for current thread - cannot reliably process 'persist' call
 */
@Transactional
@Repository
public class MessageDAO {
	// @Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Lấy các message từ database
	 * @param roomId ID của phòng
	 * @param start vị trí bắt đầu lấy
	 * @param amount số lượng record muốn lấy
	 * @return Danh sách các message, sắp xếp theo ID giảm dần
	 */
	public List<Message> getMessages(long roomId, int start, int amount) {
		String sql = "Select m from " + Message.class.getName() + " m " //
				+ "where m.room.id = :room_id order by m.id DESC";
		TypedQuery<Message> typedQuery = entityManager.createQuery(sql, Message.class);
		typedQuery.setParameter("room_id", roomId);
		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(amount);

		return typedQuery.getResultList();
	}
	
	/**
	 * Lấy các message cũ hơn từ 1 messageId cho trước, tức là nếu messageId = 123
	 * thì sẽ lấy các message có ID < 123
	 * @param roomId ID của phòng
	 * @param fromMessageId Các message trả về sẽ có ID < tham số này
	 * @param amount số lượng message cần lấy
	 * @return Danh sách các message, sắp xếp theo ID giảm dần
	 */
	public List<Message> getOlderMessagesThanMessage(long roomId, long fromMessageId, int amount) {
		String sql = "Select m from " + Message.class.getName() + " m " //
				+ "where m.room.id = :room_id and m.id < :fromMessageId " //
				+ "order by m.id DESC";
		TypedQuery<Message> typedQuery = entityManager.createQuery(sql, Message.class);
		typedQuery.setParameter("room_id", roomId);
		typedQuery.setParameter("fromMessageId", fromMessageId);
		typedQuery.setMaxResults(amount);

		return typedQuery.getResultList();
	}

	public long insertMessage(Message msg, long userId, long roomId) {
		User sender = entityManager.getReference(User.class, userId);
		Room roomSendTo = entityManager.getReference(Room.class, roomId);
		
		msg.setUser(sender);
		msg.setRoom(roomSendTo);
		
		entityManager.persist(msg);
		
		// The ID is only guaranteed to be generated at flush time. 
		// Persisting an entity only makes it "attached" to the persistence context.
		// So, flush the entity manager explicitely and we cant retrieve ID:
		entityManager.flush();
		
		return msg.getId();
	}
	
	/*
	 * Nếu không có @Transactional thì ta phải tạo transaction thủ công như ở dưới.
	 * Tuy nhiên sẽ xảy ra lỗi:
	 * Not allowed to create transaction on shared EntityManager - use Spring transactions or EJB CMT instead
	 * Có vẻ như Spring sẽ quản lý tất cả các transaction, do đó ta không
	 * dùng được cách này! Tức là chỉ cần thêm: @Transactional trước method hoặc class
	 * thì spring tự động quản lý, khi method có lỗi thì nó tự rollback, khi kết thúc
	 * method thì nó tự commit
	 */
	@Deprecated
	public long insertMessageWithoutTransactionAnnotation(Message msg, long userId, long roomId) {
		try {
			entityManager.getTransaction().begin();

			User sender = entityManager.getReference(User.class, userId);
			Room roomSendTo = entityManager.getReference(Room.class, roomId);
			
			msg.setUser(sender);
			msg.setRoom(roomSendTo);
			
			entityManager.persist(msg);
			
			// The ID is only guaranteed to be generated at flush time. 
			// Persisting an entity only makes it "attached" to the persistence context.
			// So, flush the entity manager explicitely and we cant retrieve ID:
			entityManager.flush();

			entityManager.getTransaction().commit();
			return msg.getId();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return -1;
		}
	}
}
