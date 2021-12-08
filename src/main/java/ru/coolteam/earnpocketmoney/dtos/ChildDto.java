package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import ru.coolteam.earnpocketmoney.models.Child;

@Data
public class ChildDto {
    private String login;
    private String password;

    public ChildDto(Child child) {
        this.login = child.getLogin();
        this.password = child.getPassword();
    }
}
