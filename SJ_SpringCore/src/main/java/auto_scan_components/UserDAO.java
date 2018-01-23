package auto_scan_components;

import org.springframework.stereotype.Repository;

@Repository()
public class UserDAO {

	public void findUser(int id) {
		System.out.println("[UserDAO] find user by id = " + id);
	}

}
