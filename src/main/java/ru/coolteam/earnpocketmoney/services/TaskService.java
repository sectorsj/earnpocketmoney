package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task createTask(String title, Integer cost) {
        Task task = new Task();
        task.setTitle(title);
        task.setCost(cost);
        task = taskRepository.save(task);
        return task;
    }
}
