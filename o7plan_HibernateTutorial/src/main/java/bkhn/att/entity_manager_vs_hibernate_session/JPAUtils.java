package bkhn.att.entity_manager_vs_hibernate_session;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Khá giống với HibernateUtils
 */
public class JPAUtils {
	private static final EntityManagerFactory 
		entityManagerFactory = buildEntityManagerFactory();
	
	/*
	 * CHÚ Ý: file config phải có tên là: persistence.xml
	 * và phải đặt trong thư mục classpath:META-INF/
	 */
	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			// Lấy thông tin từ file META-INF/persistence.xml để tạo 
			// đối tượng EntityManagerFactory
			return Persistence.createEntityManagerFactory("persistence_hehehe");
		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void shutdown() {
		getEntityManagerFactory().close();
	}
}
