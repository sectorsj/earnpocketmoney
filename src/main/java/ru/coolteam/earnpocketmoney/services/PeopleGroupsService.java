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

    public PeopleGroups findByName(String name){
        return peopleGroupsRepository.findByName(name);
    }

    public PeopleGroups savePeopleGroups (PeopleGroups peopleGroups){
       return   peopleGroupsRepository.save(peopleGroups);
    }







}
