package ru.coolteam.earnpocketmoney.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
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

    public Optional<Bonus> findByName (String title) {
        return bonusRepository.findFirstBonusByTitle(title);
    }

    public Bonus createBonus (String title, Parent parent, Integer price){
        Bonus bonus = new Bonus();
        bonus.setTitle(title);
        bonus.setParent(parent);
        bonus.setPrice(price);
        return bonusRepository.save(bonus);
    }

    //метод для корректировки стоимости бонуса родителем
    public Bonus updateBonusFromParent (String title, Parent parent, Integer price){
        Bonus bonus = findByName(title).get();
        bonus.setPrice(price);
        return bonusRepository.save(bonus);

    }

    //метод для корректировки бонуса со стороны ребенка (получение)
    public Bonus updateBonusFromChildren (String title, Child child, LocalDateTime localDateTime){
        Bonus bonus = findByName(title).get();
        bonus.setChild(child);
        bonus.setReceivedAt(localDateTime);
        return bonusRepository.save(bonus);
    }

    public boolean delete (String title){
        Bonus bonus = findByName(title).get();
        bonusRepository.delete(bonus);
        return true;
    }

}
