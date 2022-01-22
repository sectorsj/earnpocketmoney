package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskByTitle (String login);
    List<Task> findTaskByUserCreatingTaskRoleContains (Role role);
    List<Task> findTaskByUserCreatingTask_PeopleGroups (PeopleGroups peopleGroups);
    List<Task> findTaskByUserCreatingTask (User user);
    List<Task> findTaskByUserExecutingTask (User user);
    List<Task> findAllByUserExecutingTask_PeopleGroups (PeopleGroups peopleGroups);
    List<Task> findTasksByUserExecutingTaskAndStatusOrderByUpdatedAtAsc (User userExecutingTask, Status status);

}