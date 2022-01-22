package ru.coolteam.earnpocketmoney.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.BonusRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BonusService {
    private final BonusRepository bonusRepository;

    public Optional<Bonus> findById(Integer id) {
        return bonusRepository.findById(id);
    }

    public List<Bonus> findAll() {
        return bonusRepository.findAll();
    }

    public List<Bonus> getAllBonusesByPeopleGroups(PeopleGroups peopleGroups) {
        return bonusRepository.findBonusByUserCreatingBonus_PeopleGroups(peopleGroups);
    }

    public List<Bonus> getAllBonusesByUserCreatingBonus (User userCreatingBonus){
        return bonusRepository.findBonusByUserCreatingBonus(userCreatingBonus);
    }

    public List<Bonus> getAllBonusesByUserGettingBonus (User userGettingBonus){
        return bonusRepository.findBonusByUserGettingBonus(userGettingBonus);
    }

    public Optional<Bonus> findByName (String title) {
        return bonusRepository.findFirstBonusByTitle(title);
    }

    public Bonus createBonus (String title, User userCreatingBonus, Long price){
        Bonus bonus = new Bonus();
        bonus.setTitle(title);
        bonus.setUserCreatingBonus(userCreatingBonus);
        bonus.setPrice(price);
        return bonusRepository.save(bonus);
    }

    //метод для корректировки стоимости бонуса родителем
    public Bonus updateBonusFromParent (String title, User userCreatingBonus, Long price){
        Bonus bonus = findByName(title).get();
        bonus.setPrice(price);
        return bonusRepository.save(bonus);

    }

    //метод для корректировки бонуса со стороны ребенка (получение)
    public Bonus updateBonusFromChildren (String title, User userGettingBonus, LocalDateTime localDateTime){
        Bonus bonus = findByName(title).get();
        bonus.setUserGettingBonus(userGettingBonus);
        bonus.setUpdatedAt(localDateTime);
        return bonusRepository.save(bonus);
    }

    public boolean delete (String title){
        Bonus bonus = findByName(title).get();
        bonusRepository.delete(bonus);
        return true;
    }



}
