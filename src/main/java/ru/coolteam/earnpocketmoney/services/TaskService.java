package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.repositories.TaskRepository;

import java.time.LocalDateTime;
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

    public List<Task> findAllByChild(Child child){
        return taskRepository.findAllByChild(child);
    }

    public Task createTask(String title, Parent parent, Child child, Integer cost) {
        Task task = new Task();
        task.setTitle(title);
        task.setParent(parent);
        task.setChild(child);
        task.setCost(cost);
        return taskRepository.save(task);
    }

    public Optional<Task> findByTitle (String title) {
        return taskRepository.findTaskByTitle(title);
    }

    public Task updatedTime (String title, LocalDateTime updatedAt){
        Task task = taskRepository.findTaskByTitle(title).get();
        task.setUpdatedAt(updatedAt);
        return taskRepository.save(task);
    }

    public Task updateChild (String title, Child child){
        Task task = taskRepository.findTaskByTitle(title).get();
        task.setChild(child);
        return task;
    }

    public boolean delete (String title){
        Task task = taskRepository.findTaskByTitle(title).get();
        taskRepository.delete(task);
        return true;
    }


}
