package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.dtos.ParentDto;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.services.ParentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parents")
public class ParentController {
    private final ParentService parentService;

    @GetMapping()
    public List<Parent> getAllParents() {
        return parentService.findAll();
    }

    @GetMapping("/dto")
    public List<ParentDto> getAllParentsDto() {
        return parentService.findAll().stream().map(ParentDto::new).collect(Collectors.toList());
    }
}
