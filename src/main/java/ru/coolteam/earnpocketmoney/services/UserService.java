package ru.coolteam.earnpocketmoney.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

       public User findByLogin (String login){
        return userRepository.findByLogin(login);
    }


    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
