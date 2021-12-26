package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.*;
import ru.coolteam.earnpocketmoney.repositories.PeopleGroupsRepository;
import ru.coolteam.earnpocketmoney.repositories.StatusRepository;
import ru.coolteam.earnpocketmoney.repositories.TaskRepository;
import ru.coolteam.earnpocketmoney.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final PeopleGroupsRepository peopleGroupsRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksByPeopleGroups (String groupName){
        return taskRepository.findTaskByUserCreatingTask_PeopleGroups(peopleGroupsRepository.findByName(groupName));
    }

    public List<Task> getAllTasksByUserCreatingTask (String login){
        return taskRepository.findTaskByUserCreatingTask(userRepository.findByLogin(login));
    }

    public List<Task> getAllTasksByUserExecutingTask (String login){
        return taskRepository.findTaskByUserExecutingTask(userRepository.findByLogin(login));
    }

    public Task createTask (String title,
                            String taskText,
                            User userCreatingTask,
                            User userExecutingTask,
                            Long wages){

        Task task = new Task();
        task.setTitle(title);
        task.setTaskText(taskText);
        task.setUserCreatingTask(userCreatingTask);
        task.setUserExecutingTask(userExecutingTask);
        task.setStatus(statusRepository.getById(1L));
        task.setWages(wages);

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

    public Task updateUserExecutingTasks (String title, User userExecutingTasks){
        Task task = taskRepository.findTaskByTitle(title).get();
        task.setUserExecutingTask(userExecutingTasks);
        return task;
    }

    public boolean delete (String title){
        Task task = taskRepository.findTaskByTitle(title).get();
        taskRepository.delete(task);
        return true;
    }


}
