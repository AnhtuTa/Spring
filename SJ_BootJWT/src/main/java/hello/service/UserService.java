package hello.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import hello.common.Result;
import hello.entity.User;
import hello.model.LoginRequest;
import hello.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public boolean add(User user) {
        userRepository.save(user);
        return true;
    }

    public void delete(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        }
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkLogin(LoginRequest loginRequest) {
        try {
            // Xác thực từ username và password.
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Result getMe() {
        Result result = new Result();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                User user = userRepository.findByUsername(currentUserName);
                result.successRes(user);
            } else {
                result.failRes(null);
            }
        } catch (Exception e) {
            result.failRes(e.getMessage());
        }

        return result;
    }
}