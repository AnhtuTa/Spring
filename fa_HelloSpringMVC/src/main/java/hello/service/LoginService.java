package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Fresher;
import hello.repository.FresherRepository;

@Service
public class LoginService {
	@Autowired
	private FresherRepository fresherRepository;
	
	public boolean checkLogin(Fresher f) {
		return fresherRepository.authenticate(f);
	}
}
