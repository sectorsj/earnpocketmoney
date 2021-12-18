package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Role;

@Data
@NoArgsConstructor
public class ChildDto {

    private String login;
    private String password;
    private Integer wallet;
    private Role role;

    public ChildDto(Child child) {
        this.login = child.getLogin();
        this.password = child.getPassword();
        this.wallet = child.getWallet();
        this.role = child.getRole();
    }
}
