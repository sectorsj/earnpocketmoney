package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.services.BonusService;
import ru.coolteam.earnpocketmoney.services.ChildService;
import ru.coolteam.earnpocketmoney.services.ParentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bonuses")
public class BonusController {
    private final BonusService bonusService;
    private final ParentService parentService;
    private final ChildService childService;



    @GetMapping()
    public List<BonusDto> getAllChildren() {
        return  bonusService.findAll().stream().map(BonusDto::new).collect(Collectors.toList());
    }

    @GetMapping("/getId")
    public Optional<BonusDto> getBonusDtoById(@RequestParam Integer id){
        return bonusService.findById(id).map(BonusDto::new);
    }

    @GetMapping("/getTitle")
    public Optional<BonusDto> getBonusDtoByTitle(@RequestParam String title){
        return bonusService.findByName(title).map(BonusDto::new);
    }

    @GetMapping("/create")
    public Optional<BonusDto> create (@RequestParam String title,
                                      @RequestParam Integer idParent,  //TODO надо обдумать с какого места отправить родителя в запрос
                                      @RequestParam Integer price){
        Parent parent = parentService.findById(idParent).get();
        Bonus bonus = bonusService.createBonus(title, parent, price);
        return Optional.of(new BonusDto(bonus));
    }

    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        bonusService.delete(title);
        return true;
    }

    @GetMapping("/updateFromParent")
    public Optional<BonusDto> updateBonusFromParent (@RequestParam String title,
                                           @RequestParam Integer idParent,  //TODO надо обдумать с какого места отправить родителя в запрос
                                           @RequestParam Integer price){
        Parent parent = parentService.findById(idParent).get();
        return Optional.of(new BonusDto(bonusService.updateBonusFromParent(title,parent,price)));
    }

    @GetMapping("/updateFromChild")
    public Optional<BonusDto> updateBonusFromChild (@RequestParam String title,
                                                     @RequestParam Integer idChild  //TODO надо обдумать с какого места отправить родителя в запрос
                                                     ){
        Child child = childService.findById(idChild).get();
        return Optional.of(new BonusDto(bonusService.updateBonusFromChildren(title,child,LocalDateTime.now())));
    }



}
