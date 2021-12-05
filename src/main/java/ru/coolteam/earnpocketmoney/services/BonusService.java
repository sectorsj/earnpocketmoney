package ru.coolteam.earnpocketmoney.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.repositories.BonusRepository;

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

}
