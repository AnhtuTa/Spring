package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.common.ResponseStatus;
import hello.entity.User;
import hello.exception.RestException;
import hello.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User checkUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new RestException(ResponseStatus.USER_NOT_FOUND);
        return user;
    }

}
