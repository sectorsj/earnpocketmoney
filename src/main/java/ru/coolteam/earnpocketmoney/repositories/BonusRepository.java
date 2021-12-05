package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Bonus;

@Repository
public interface BonusRepository extends JpaRepository<Bonus,Integer> {



}
