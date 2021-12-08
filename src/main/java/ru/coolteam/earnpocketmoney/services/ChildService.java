package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.repositories.ChildRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;

    public Optional<Child> findById(Integer id) {
        return childRepository.findById(id);
    }

    public List<Child> findAll() {
        return childRepository.findAll();
    }

    public Optional<Child> findByLogin (String login) {
        return childRepository.findChildByLogin(login);
    }

    public Child createChild (String login, String password){
        Child child = new Child();
        child.setLogin(login);
        child.setPassword(password);
        return childRepository.save(child);
    }

    public Child updateChild (String login, Integer wallet){
        Child child = findByLogin(login).get();
        child.setWallet(wallet);
        return childRepository.save(child);
    }

}
