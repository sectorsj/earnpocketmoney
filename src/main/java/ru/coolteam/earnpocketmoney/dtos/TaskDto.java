package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import ru.coolteam.earnpocketmoney.models.Task;

@Data
public class TaskDto {
    private String title;

    public TaskDto(Task task) {
        this.title = task.getTitle();
    }
}
