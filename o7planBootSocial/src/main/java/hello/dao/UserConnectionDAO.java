/**
 * 
 */
package hello.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hello.entity.UserConnection;

@Repository
@Transactional
public class UserConnectionDAO {
	@Autowired
	private EntityManager entityManager;

	public UserConnection findByUserProviderId(String userProviderId) {
		try {
			String sql = "Select uc from " + UserConnection.class.getName() + " uc " //
					+ " Where uc.userProviderId = :userProviderId ";

			Query query = entityManager.createQuery(sql, UserConnection.class);
			query.setParameter("userProviderId", userProviderId);

			@SuppressWarnings("unchecked")
			List<UserConnection> list = query.getResultList();

			return list.isEmpty() ? null : list.get(0);
		} catch (NoResultException e) {
			return null;
		}
	}
}
