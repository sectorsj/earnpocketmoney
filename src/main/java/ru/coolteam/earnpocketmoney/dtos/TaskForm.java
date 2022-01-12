package ru.coolteam.earnpocketmoney.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskForm {
    private String title;
    private String taskText;
    private String userExecutingTask;
    private Long wages;


}
