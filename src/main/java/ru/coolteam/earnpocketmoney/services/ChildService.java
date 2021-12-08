package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.repositories.ChildRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;

    public Optional<Child> findById(Long id) {
        return childRepository.findById(id);
    }

    public List<Child> findAll() {
        return childRepository.findAll();
    }
}
