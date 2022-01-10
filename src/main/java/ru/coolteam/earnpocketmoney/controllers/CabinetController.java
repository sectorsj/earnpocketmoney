package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CabinetController {
    private final TaskService taskService;
    private final UserService userService;

//    Доступ в кабинет
//    TODO для кабинета необходимо добавить 1)инфу о юзере вошедшем в кабинет 2)список членов семьи (дети, родители)
//    @GetMapping("/cabinet")
//    public String getCabinet(Model model, String login) {
//        List<TaskDto> taskDtoList = taskService.findAll()
//                .stream()
//                .map(TaskDto::new)
//                .collect(Collectors.toList());
//
//        model.addAttribute("tasks", taskDtoList);
//        return "cabinet";
//    }


    @GetMapping("/cabinet")
    public String getTasksByUser (Principal principal, Model model){
        List<TaskDto> taskDtoList = new ArrayList<>();
        User user = userService.findByLogin(principal.getName());
        if(user.getRole().getRole().equals("ROLE_PARENT")){
            taskDtoList = taskService.getAllTasksByUserCreatingTask(principal.getName())
                    .stream()
                    .map(TaskDto::new)
                    .collect(Collectors.toList());
        }else if (user.getRole().getRole().equals("ROLE_CHILDREN")){
            taskDtoList = taskService.getAllTasksByUserExecutingTask(principal.getName())
                    .stream()
                    .map(TaskDto::new)
                    .collect(Collectors.toList());
        }

        user.getLogin();
        model.addAttribute("tasks", taskDtoList);
        model.addAttribute("user", user);
        return "cabinet";
    }
}
