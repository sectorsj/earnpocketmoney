package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Task;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskDto {
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ParentDto parentDto;
    private ChildDto childDto;
    private Integer cost;

    public TaskDto(Task task) {
        this.title = task.getTitle();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.parentDto = new ParentDto(task.getParent());
        if(task.getChild()!=null) {
            this.childDto = new ChildDto(task.getChild());
        }else {
            this.childDto = new ChildDto();
        }
        this.cost = task.getCost();
    }
}
