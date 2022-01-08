package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

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
    @GetMapping("/cabinet")
    public String getCabinet(Model model, String login) {
        List<TaskDto> taskDtoList = taskService.findAll()
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());

        model.addAttribute("tasks", taskDtoList);
        return "cabinet";
    }
}
