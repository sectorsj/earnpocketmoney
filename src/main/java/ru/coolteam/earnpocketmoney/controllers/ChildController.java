package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.services.ChildService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/children")
public class ChildController {
    private final ChildService childService;

    @GetMapping()
    public List<Child> getAllChildren() {
        return childService.findAll();
    }
}
