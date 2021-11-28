package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/create")
    public String createNewTask(@RequestParam(name = "title") String title) {
        taskService.createTask(title);
        return "New task = " + title;
    }
}
