package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.dtos.TaskForm;
import ru.coolteam.earnpocketmoney.dtos.UserInfo;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/api/v1/tasks")
@RequestMapping("/api/v1/")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final RoleRepository roleRepository;


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

//    @PreAuthorize("hasRole('ROLE_PARENT')")
//    @GetMapping("/create")
//    public TaskDto create (@RequestParam String title,
//                           @RequestParam String taskText,
//                           @RequestParam String userCreatingTaskLogin,
//                           @RequestParam String userExecutingTaskLogin,
//                           @RequestParam Long wages){
//        User userCreatingTask = userService.findByLogin(userCreatingTaskLogin);
//        User userExecutingTask = userService.findByLogin(userExecutingTaskLogin);
//
//        return new TaskDto(taskService.createTask(title, taskText, userCreatingTask, userExecutingTask, wages));
//    }

//   @GetMapping("/tasks/create")
//   public String createTask(Model model) {
//       model.addAttribute("taskForm", new Task());
//       return "tasks";
//   }

   @GetMapping("/tasks")
   public String createTask(Principal principal, Model model) {
       User user = userService.findByLogin(principal.getName());
       Role role = roleRepository.findByRole("ROLE_CHILDREN");

       List<UserInfo> userInfoList = userService.findAllByPeopleGroupsAndRole(user.getPeopleGroups(), role)
               .stream()
               .map(UserInfo::new)
               .collect(Collectors.toList());
       model.addAttribute("users" , userInfoList);

       TaskForm taskForm = new TaskForm();
       taskForm.setWages(5L);
       model.addAttribute("taskForm", taskForm);

       return "tasks";
   }


    @PostMapping("/tasks")
    public String createTask(@Valid @ModelAttribute("taskForm") TaskForm taskForm, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "tasks";
        }
        User user = userService.findByLogin(principal.getName());
        User userExecutingTask = null;
        if(!taskForm.getUserExecutingTask().equals("")){
            userExecutingTask = userService.findByLogin(taskForm.getUserExecutingTask());
        }

        //TODO исправить ручное введение стоимости!!
       // taskForm.setWages(5L);

        taskService.createTask(taskForm.getTitle(),
                taskForm.getTaskText(),
                user,
                userExecutingTask,
                taskForm.getWages());

//        return "redirect:/api/v1/tasks/all";
        return "redirect:/api/v1/cabinet";
    }

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @DeleteMapping("/delete")
    public boolean delete (@RequestParam String title){
        taskService.delete(title);
        return true;
    }
}
