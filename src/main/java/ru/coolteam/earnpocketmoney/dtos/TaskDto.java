package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import ru.coolteam.earnpocketmoney.models.Task;
import ru.coolteam.earnpocketmoney.models.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskDto {
    private String title;

    public TaskDto(Task task) {
        this.title = task.getTitle();
=======
    private String taskText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto userCreatingTask;
    private UserDto userExecutingTask;
    private StatusDto status;
    private Long wages;
//    private Integer cost;

    public TaskDto(Task task) {
        this.title = task.getTitle();
        this.taskText = task.getTaskText();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        /*this.parentDto = new ParentDto(task.getParent());
        if(task.getChild()!=null) {
            this.childDto = new ChildDto(task.getChild());
        }else {
            this.childDto = new ChildDto();
        }
        this.cost = task.getCost();*/
        this.userCreatingTask = new UserDto(task.getUserCreatingTask());
        if(task.getUserExecutingTask()!=null){
        this.userExecutingTask = new UserDto(task.getUserExecutingTask());
        }else {
            this.userExecutingTask = new UserDto();
        }
        this.status = new StatusDto(task.getStatus());
        this.wages = task.getWages();


>>>>>>> dev_front
    }
}
