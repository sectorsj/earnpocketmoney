package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.services.BonusService;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/bonuses")
public class BonusController {
    private final BonusService bonusService;
    private final UserService userService;


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

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @GetMapping("/create")
    public Optional<BonusDto> create (@RequestParam String title,
                                      @RequestParam String userCreatingBonusLogin,  // TODO надо обдумать с какого места отправить родителя в запрос
                                      @RequestParam Long price){
        User userCreatingBonus = userService.findByLogin(userCreatingBonusLogin);
        Bonus bonus = bonusService.createBonus(title, userCreatingBonus, price);
        return Optional.of(new BonusDto(bonus));
    }

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        bonusService.delete(title);
        return true;
    }

    @GetMapping("/updateFromParent")
    public Optional<BonusDto> updateBonusFromParent (@RequestParam String title,
                                           @RequestParam String userCreatingBonusLogin,  // TODO надо обдумать с какого места отправить родителя в запрос
                                           @RequestParam Long price){
        User userCreatingBonus = userService.findByLogin(userCreatingBonusLogin);
        return Optional.of(new BonusDto(bonusService.updateBonusFromParent(title,userCreatingBonus,price)));
    }

    @GetMapping("/updateFromChild")
    public Optional<BonusDto> updateBonusFromChild (@RequestParam String title,
                                                     @RequestParam String userGettingBonusLogin)  // TODO надо обдумать с какого места отправить родителя в запрос
    {
        User userGettingBonus = userService.findByLogin(userGettingBonusLogin);
        return Optional.of(new BonusDto(bonusService.updateBonusFromChildren(title,userGettingBonus,LocalDateTime.now())));
    }
}
