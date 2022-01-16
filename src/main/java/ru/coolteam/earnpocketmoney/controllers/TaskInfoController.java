package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.dtos.TaskDto;
import ru.coolteam.earnpocketmoney.dtos.TaskForm;
import ru.coolteam.earnpocketmoney.models.Status;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.models.Wallet;
import ru.coolteam.earnpocketmoney.repositories.StatusRepository;
import ru.coolteam.earnpocketmoney.services.TaskService;
import ru.coolteam.earnpocketmoney.services.UserService;
import ru.coolteam.earnpocketmoney.services.WalletService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TaskInfoController {

    private final TaskService taskService;
    private final UserService userService;
    private final StatusRepository statusRepository;
    private final WalletService walletService;


    @GetMapping("/task_info/{id}")
    public String getTaskInfo (@PathVariable Long id, Principal principal, Model model){
        TaskDto taskDto = new TaskDto(taskService.findById(id).orElseThrow());
        model.addAttribute("taskDto", taskDto);
        return "task_info";
    }

    @GetMapping("/task_info/accepted/{id}")
    public String acceptedTask (@PathVariable Long id,Principal principal, Model model ) {
        updatingStatusTask(id,2L);
        return "redirect:/api/v1/cabinet";
    }


    @GetMapping("/task_info/end/{id}")
    public String executedTask (@PathVariable Long id,Principal principal, Model model ) {
         updatingStatusTask(id,3L);
        return "redirect:/api/v1/cabinet";
    }


    @GetMapping("/task_info/end/confirmation/{id}")
    public String confirmationTask (@PathVariable Long id,Principal principal, Model model ) {
        //updatingStatusTask(id,4L);
        Status status = statusRepository.getById(4L);
        Task task = taskService.findById(id).orElseThrow();
        task.setStatus(status);
        User user = task.getUserExecutingTask();
        Wallet wallet = user.getWallet();
        wallet.setValue( wallet.getValue() + task.getWages());
        walletService.saveWallet(wallet);
        taskService.update(task);
        return "redirect:/api/v1/cabinet";
    }

    private void updatingStatusTask (Long taskId, Long statusId){
        Status status = statusRepository.getById(statusId);
        Task task = taskService.findById(taskId).orElseThrow();
        task.setStatus(status);
        taskService.update(task);
    }


}
