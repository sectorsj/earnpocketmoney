package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coolteam.earnpocketmoney.dtos.UserDto;
import ru.coolteam.earnpocketmoney.dtos.UserInfo;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class FamilyController {
    private final TaskService taskService;
    private final UserService userService;
    private final RoleRepository roleRepository;


    @GetMapping("/family")
    public String getAllChildrenByFamily (Principal principal, Model model){
        List<UserDto> userDtoList;
        User user = userService.findByLogin(principal.getName());

        List<UserInfo> userParentInfoList = userService
                .findAllByPeopleGroupsAndRole(user.getPeopleGroups(), roleRepository.findByRole("ROLE_PARENT"))
                .stream()
                .map(UserInfo::new)
                .collect(Collectors.toList());

        List<UserInfo> userChildrenInfoList = userService
                .findAllByPeopleGroupsAndRole(user.getPeopleGroups(), roleRepository.findByRole("ROLE_CHILDREN"))
                .stream()
                .map(UserInfo::new)
                .collect(Collectors.toList());

        model.addAttribute("children", userChildrenInfoList );
        model.addAttribute("parent", userParentInfoList);
        model.addAttribute("user", user);

        return "family";
    }


}
