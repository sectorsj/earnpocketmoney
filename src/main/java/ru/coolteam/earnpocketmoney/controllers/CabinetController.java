package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.dtos.TaskForm;
import ru.coolteam.earnpocketmoney.dtos.UserDto;
import ru.coolteam.earnpocketmoney.dtos.UserInfo;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.repositories.StatusRepository;
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
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;

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

        //наверное на потом подобную выборку отложим, оставим отображение всех задач созданных в семье
      /*  if(user.getRole().getRole().equals("ROLE_PARENT")){
            taskDtoList = taskService.getAllTasksByUserCreatingTask(principal.getName())
                    .stream()
                    .map(TaskDto::new)
                    .collect(Collectors.toList());
        }else if (user.getRole().getRole().equals("ROLE_CHILDREN")){
            taskDtoList = taskService.getAllTasksByUserExecutingTask(principal.getName())
                    .stream()
                    .map(TaskDto::new)
                    .collect(Collectors.toList());
        }*/

        taskDtoList = taskService.getAllTasksByPeopleGroups(user.getPeopleGroups().getName())
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());



        user.getLogin();
        List<UserInfo> userInfoList = userService.findAllByPeopleGroups(user.getPeopleGroups())
                .stream()
                .map(UserInfo::new)
                .collect(Collectors.toList());

        List<TaskDto> executingTaskDtoList = taskService.findTasksByUserAndStatus(user, statusRepository.getById(2L))
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());

        TaskDto executingTaskDto = null;
        if(!executingTaskDtoList.isEmpty()){
            executingTaskDto = executingTaskDtoList.get(0);
        }


        model.addAttribute("myFamily" ,userInfoList);
        model.addAttribute("tasks", taskDtoList);
        model.addAttribute("user", user);
        model.addAttribute("executingTaskDtoList", executingTaskDtoList);
        model.addAttribute("executingTaskDto", executingTaskDto);
        model.addAttribute("taskForm", new TaskForm());
        return "cabinet";
    }






}
