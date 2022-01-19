package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.dtos.BonusForm;
import ru.coolteam.earnpocketmoney.dtos.UserInfo;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.BonusService;
import ru.coolteam.earnpocketmoney.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
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
    private final RoleRepository roleRepository;

    @GetMapping()
    public String getAllBonuses(Principal principal, Model model){
        User user = userService.findByLogin(principal.getName());

        Role role = roleRepository.findByRole("ROLE_CHILDREN");

        List<UserInfo> userInfoList = userService.findAllByPeopleGroupsAndRole(user.getPeopleGroups(), role)
                .stream()
                .map(UserInfo::new)
                .collect(Collectors.toList());
        model.addAttribute("children" , userInfoList);

        List<BonusDto> bonusDtoList = bonusService
                .getAllBonusesByPeopleGroups(user.getPeopleGroups())
                .stream()
                .map(BonusDto::new)
                .collect(Collectors.toList());
        model.addAttribute("bonusDtoList", bonusDtoList);
        model.addAttribute("bonusForm",new BonusForm());
        model.addAttribute("user", user);
        return "bonuses";
    }

    @PostMapping()
    public String getAllBonuses(@Valid @ModelAttribute("bonusForm") BonusForm bonusForm, Principal principal, Model model){
        Bonus bonus = new Bonus();
        bonus.setUserCreatingBonus(userService.findByLogin(principal.getName()));
        if(!bonusForm.getUserGettingBonus().equals("")) {
            bonus.setUserGettingBonus(userService.findByLogin(bonusForm.getUserGettingBonus()));
        }else {
            bonus.setUserGettingBonus(null);
        }
        bonus.setTitle(bonusForm.getTitle());
        bonus.setBonusText(bonusForm.getBonusText());
        bonus.setPrice(bonusForm.getPrice());
        bonusService.save(bonus);
        return "redirect:/api/v1/bonuses";
    }


    /*@GetMapping()
    public List<BonusDto> getAllChildren() {
        return  bonusService.findAll().stream().map(BonusDto::new).collect(Collectors.toList());
    }*/

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
