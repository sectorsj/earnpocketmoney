package ru.coolteam.earnpocketmoney.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.repositories.PeopleGroupsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleGroupsService {

    private final PeopleGroupsRepository peopleGroupsRepository;

<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/services/ChildService.java
    public Optional<Child> findById(Long id) {
        return childRepository.findById(id);
=======
    public PeopleGroups findByName(String name){
        return peopleGroupsRepository.findByName(name);
>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/services/PeopleGroupsService.java
    }

    public PeopleGroups savePeopleGroups (PeopleGroups peopleGroups){
       return   peopleGroupsRepository.save(peopleGroups);
    }







}
