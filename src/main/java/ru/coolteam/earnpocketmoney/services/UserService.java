package ru.coolteam.earnpocketmoney.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login).orElseThrow();
    }

    public Optional<User> findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return Optional.empty();
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

       return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }


    public List<User> findAllByPeopleGroups (PeopleGroups peopleGroups){
        return userRepository.findAllByPeopleGroups(peopleGroups);
    }

    public List<User> findAllByPeopleGroupsAndRole (PeopleGroups peopleGroups, Role role){
        return userRepository.findAllByPeopleGroupsAndRole(peopleGroups, role);
    }



//    public User findByLogin(String login){
//        return userRepository.findByLogin(login);
//    }

//    public User findByLogin(String login, String password) {
//        User user = findByLogin(login);
//        if (user != null) {
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return user;
//            }
//        }
//        return null;
//    }

//    public User findByEmail(String email) {
//        userRepository.findByEmail(email);
//        return null;
//    }
}
