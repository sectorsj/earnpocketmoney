package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.services.ChildService;
import ru.coolteam.earnpocketmoney.services.ParentService;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ParentService parentService;
    private final ChildService childService;
    private final UserService userService;

    @GetMapping()
    public List<TaskDto> getAllTasks() {
        return taskService.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @GetMapping("/getTitle")
    public Optional<TaskDto> getTaskDtoByTitle(@RequestParam String title){
        return taskService.findByTitle(title).map(TaskDto::new);
    }

    @GetMapping("/updateTime")
    public Optional<TaskDto> updatedTime (@RequestParam String title
                                          ){
        return Optional.of(new TaskDto(taskService.updatedTime(title, LocalDateTime.now())));
    }

    @GetMapping("/groupTasks")
    public List<TaskDto> getgroupsTaskDtos (@RequestParam String groupName){
        List<TaskDto> taskDtoList = taskService.getAllTasksByPeopleGroups(groupName)
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return taskDtoList;
    }

    @GetMapping("/userCreatingTask")
    public List<TaskDto> getTasksByUserCreatingTask (@RequestParam String login){
        List<TaskDto> taskDtoList = taskService.getAllTasksByUserCreatingTask(login)
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return taskDtoList;
    }

    @GetMapping("/userExecutingTask")
    public List<TaskDto> getTasksByUserExecutingTask (@RequestParam String login){
        List<TaskDto> taskDtoList = taskService.getAllTasksByUserExecutingTask(login)
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return taskDtoList;
    }

    @GetMapping("/create")
    public TaskDto create (@RequestParam String title,
                            @RequestParam String taskText,
                           @RequestParam String userCreatingTaskLogin,
                           @RequestParam String userExecutingTaskLogin,
                           @RequestParam Long wages){
        User userCreatingTask = userService.findByLogin(userCreatingTaskLogin);
        User userExecutingTask = userService.findByLogin(userExecutingTaskLogin);

        return new TaskDto(taskService.createTask(title, taskText, userCreatingTask, userExecutingTask, wages));
    }

    /*@GetMapping("by_role")
    public List<TaskDto> getTaskDtoByUserCreatingRole(){
        return taskService.
    }*/


   /* @GetMapping("/create")      //todo не забудь добавить description в модели и далее по коду, или удали этот столбец из таблицы в БД
    public Optional<TaskDto> create(@RequestParam(name = "title") String title,
                                           @RequestParam(name = "idParent") Integer idParent,
                                           @RequestParam(name = "idChild") Integer idChild,
                                           @RequestParam(name = "cost") Integer cost) {
        Parent parent = parentService.findById(idParent).get();
        Child child;
        if(idChild == null || idChild == 0)
            {child = null;
            }else{child  = childService.findById(idChild).get();}

        return Optional.of(new TaskDto(taskService.createTask(title, parent, child, cost)));
    }*/

    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        taskService.delete(title);
        return true;
    }



}
