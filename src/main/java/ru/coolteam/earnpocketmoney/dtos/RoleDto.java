package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Role;


@Data
@NoArgsConstructor
public class RoleDto {

  //  Integer id;
    String role;

    public RoleDto(Role role) {
   //     this.id = role.getId();
        this.role = role.getRole();
    }
}
