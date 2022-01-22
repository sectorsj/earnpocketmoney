package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus,Integer> {

    Optional <Bonus> findFirstBonusByTitle (String title);
    /*Bonus findFirstByTitle (String title);
    List<Bonus> findAllByChild (Child child);*/

    List<Bonus> findBonusByUserCreatingBonus_PeopleGroups (PeopleGroups peopleGroups);
    List<Bonus> findBonusByUserCreatingBonus (User user);
    List<Bonus> findBonusByUserGettingBonus (User user);



    @Query("SELECT i FROM Bonus i where i.title = :name ")
    Bonus findBonusByName (String name);




}
