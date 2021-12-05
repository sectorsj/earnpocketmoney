package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.services.BonusService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bonuses")
public class BonusController {
    private final BonusService bonusService;



    @GetMapping()
    public List<BonusDto> getAllChildren() {
        return  bonusService.findAll().stream().map(BonusDto::new).collect(Collectors.toList());
    }

    @GetMapping("/getId")
    public Optional<BonusDto> getBonusDtoById(@RequestParam Integer id){
        return bonusService.findById(id).map(BonusDto::new);
    }



}
