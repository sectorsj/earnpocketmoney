package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.dtos.TaskForm;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TaskInfoController {

    private final TaskService taskService;
    private final UserService userService;


    /*@PostMapping("/task_info")
    public String getTaskInfo (@Valid @ModelAttribute("taskForm") TaskForm taskForm, Principal principal, Model model){

        System.out.println("++++++++++" + taskForm.getTitle());

        return "task_info";
    }*/

    @GetMapping("/task_info/{id}")
    public String getTaskInfo (@PathVariable Integer id, Principal principal, Model model){

        System.out.println("++++++++++" + id);

        return "task_info";
    }



}
