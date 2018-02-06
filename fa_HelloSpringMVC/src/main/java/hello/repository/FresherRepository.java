package hello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.dao.FresherDAO;
import hello.model.Fresher;

@Repository
public class FresherRepository {
	
	@Autowired
	private FresherDAO fresherDAO;
	
	public boolean authenticate(Fresher f) {
		Fresher f1 = fresherDAO.getFresherByUserAndPass(f.getUsername(), f.getPassword());
		//save f1.getUsername() to session
		//...
		return f1 != null;		
	}

	public boolean authenRegister(Fresher f) {
		return fresherDAO.insertFresher(f);
	}
}
