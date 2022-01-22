package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.models.User;

import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus,Integer> {

    Optional <Bonus> findFirstBonusByTitle (String title);
    Bonus findFirstByTitle (String title);

    @Query("SELECT i FROM Bonus i where i.title = :name ")
    Bonus findBonusByName (String name);

  /*  @Modifying
    @Query("update Bonus b set b.price = :price where b.title = :title")
    void updateBonusPrice(@Param(value = "price") Integer price, @Param(value = "title") String title);
*/


}
