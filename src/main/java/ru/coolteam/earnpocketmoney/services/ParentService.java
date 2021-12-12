package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.repositories.ParentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;

    public Optional<Parent> findById(Integer id) {
        return parentRepository.findById(id);
    }

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Optional<Parent> findByLogin (String login) {
        return parentRepository.findParentByLogin(login);
    }

    public Parent createParent (String login, String password){
        Parent parent = new Parent();
        parent.setLogin(login);
        parent.setPassword(password);
        return parentRepository.save(parent);
    }

    public Parent updatePasswordParent (String login, String lastPass, String futurePass){
        Parent parent = parentRepository.findParentByLoginAndPassword(login,lastPass).get();
        parent.setPassword(futurePass);
        return parentRepository.save(parent);
    }

    public boolean delete (String login, String password){
        Parent parent= parentRepository.findParentByLoginAndPassword(login,password).get();
        parentRepository.delete(parent);
        return true;
    }


}
