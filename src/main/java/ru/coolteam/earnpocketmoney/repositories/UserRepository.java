package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

//    User findParentByLoginAndPassword(String login, String password);
//    User findByLogin(String login);
//    User findByUsername(String username);
//    User findByEmail(String email);
}
