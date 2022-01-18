package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserInfo {

    private String login;
    private String password;
    private String passwordConfirm;
    private String username;
    private Long walletSize;
    private List<TaskDto> creatingTasksList;
    private List<TaskDto> executingTasksList;
    private Integer creatingTasksListSize;
    private Integer executingTasksListSize;
    private Integer expiredTasksListSize;
    private String peopleGroupName;
    private String role;

    public UserInfo(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.walletSize = user.getWallet().getValue();
        this.creatingTasksList = user.getCreatingTasksList().stream().map(TaskDto::new).collect(Collectors.toList());
        this.executingTasksList = user.getExecutingTasksList().stream().map(TaskDto::new).collect(Collectors.toList());
        this.creatingTasksListSize = creatingTasksList.size();
        this.executingTasksListSize = executingTasksList.size();
        this.peopleGroupName = user.getPeopleGroups().getName();
        this.role = user.getRole().getRole();
        this.passwordConfirm = user.getPasswordConfirm();
    }
}
