package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coolteam.earnpocketmoney.dtos.UserDto;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/users")
@RequestMapping("api/v1/")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;


    @GetMapping("/all")
    public String getAllUsers (Model model){
        List<UserDto> userDtoList;
        userDtoList = userService.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute(userDtoList);
        return "index";
    }

//    @GetMapping("/allByFamily")
//    public String getAllUsersBeFamily (Principal principal, Model model){
//        List<UserDto> userDtoList;
//        User user = userService.findByLogin(principal.getName());
//        userDtoList = userService.findAllByPeopleGroups(user.getPeopleGroups())
//                .stream()
//                .map(UserDto::new)
//                .collect(Collectors.toList());
//        model.addAttribute(userDtoList);
//        return "index";
//    }

    @GetMapping("/familyAll")
    public String getAllUsersByFamily (Principal principal, Model model){
        List<UserDto> userDtoList;
        User user = userService.findByLogin(principal.getName());
        userDtoList = userService.findAllByPeopleGroups(user.getPeopleGroups())
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute(userDtoList);
        return "family";
    }

/*    @GetMapping("/family")
    public String getAllChildrenByFamily (Principal principal, Model model){
        List<UserDto> userDtoList;
        User user = userService.findByLogin(principal.getName());
        Role role = roleRepository.findByRole("ROLE_CHILDREN");
        userDtoList = userService.findAllByPeopleGroupsAndRole(user.getPeopleGroups(), role)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute("children" , userDtoList);
        return "family";
    }*/





}
