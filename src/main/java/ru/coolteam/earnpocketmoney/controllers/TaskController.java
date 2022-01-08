package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

//    rest version
//    @GetMapping()
//    public List<TaskDto> getAllTasks() {
//        List<TaskDto> taskDtoList = taskService.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
//        return taskDtoList;
//    }

//    @GetMapping()
//    public String getAllTasks(Model model) {
//        List<TaskDto> taskDtoList = taskService.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
//        model.addAttribute("tasks", taskDtoList);
//        return "tasklist";
//    }


//     Вывести весь список задач
//    @GetMapping("/tasks/all")
//    public String getAllTasks(Model model) {
//        List<TaskDto> taskDtoList = taskService.findAll()
//                .stream()
//                .map(TaskDto::new)
//                .collect(Collectors.toList());
//
//        model.addAttribute("tasks", taskDtoList);
//        return "tasks";
//    }

    // Вывести весь список задач
    @GetMapping("/all")
    public String getAllTasks() {
//        List<TaskDto> taskDtoList = taskService.findAll()
//                .stream()
//                .map(TaskDto::new)
//                .collect(Collectors.toList());
//
//        model.addAttribute("tasks", taskDtoList);
        return "tasks";
    }

//    @GetMapping("/{id}")
//    public String showTaskInfo (@PathVariable(name = "id") Long id, Model model) {
//        Optional<Task> task = taskService.findById(id);
//        if (task.isPresent()) {
//            model.addAttribute("task", task.get());
//        }
//        return "task_info";
//    }

    // Найти задачу по ID
    @GetMapping("/{id}")
    public String showTaskInfo (@PathVariable(name = "id") Long id, Model model) {
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
        }
        return "task_info";
    }

    // Поиск по Заголовку Задачи
    @GetMapping("/getTitle")
    public TaskDto getTaskDtoByTitle(@RequestParam String title){
        TaskDto taskDto = new TaskDto(taskService.findByTitle(title).get()) ;
        return taskDto;
    }

    // Обновление времени создания Задачи
    @GetMapping("/updateTime")
    public TaskDto updatedTime (@RequestParam String title){
        TaskDto taskDto = new TaskDto(taskService.updatedTime(title, LocalDateTime.now()));
        return taskDto;
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

    @PreAuthorize("hasRole('ROLE_PARENT')")
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

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        taskService.delete(title);
        return true;
    }
}
