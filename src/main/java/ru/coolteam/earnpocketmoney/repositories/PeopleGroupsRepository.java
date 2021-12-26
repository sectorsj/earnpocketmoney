package ru.coolteam.earnpocketmoney.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;

@Repository
public interface PeopleGroupsRepository extends JpaRepository< PeopleGroups, Long> {
    PeopleGroups findByName (String groupName);

}
