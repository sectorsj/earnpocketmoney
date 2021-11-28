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

    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }
}
