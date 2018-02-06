package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Fresher;
import hello.repository.FresherRepository;

@Service
public class RegisterService {
	@Autowired
	private FresherRepository fresherRepository;
	
	public boolean checkRegister(Fresher f) {
		return fresherRepository.authenRegister(f);
	}
}
