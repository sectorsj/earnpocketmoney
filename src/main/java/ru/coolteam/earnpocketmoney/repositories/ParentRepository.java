package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Parent;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {

    Optional<Parent> findParentByLogin (String login);
    Optional<Parent> findParentByLoginAndPassword (String login, String password);

}
