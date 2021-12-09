package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.BonusDto;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.services.ChildService;
import ru.coolteam.earnpocketmoney.services.ParentService;
import ru.coolteam.earnpocketmoney.services.TaskService;

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

    @GetMapping("/create")
    public Optional<TaskDto> createTask(@RequestParam(name = "title") String title,
                                           @RequestParam(name = "idParent") Integer idParent,
                                           @RequestParam(name = "idChild") Integer idChild,
                                           @RequestParam(name = "cost") Integer cost) {
        Parent parent = parentService.findById(idParent).get();
        Child child  = childService.findById(idChild).get();
        return Optional.of(new TaskDto(taskService.createTask(title, parent, child, cost)));
    }

    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        taskService.delete(title);
        return true;
    }



}
