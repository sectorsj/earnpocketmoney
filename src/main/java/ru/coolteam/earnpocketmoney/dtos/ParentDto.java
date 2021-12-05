package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Parent;

@Data
@NoArgsConstructor
public class ParentDto {
    private String login;
    private String password;

    public ParentDto(Parent parent) {
        this.login = parent.getLogin();
        this.password = parent.getPassword();
    }
}
