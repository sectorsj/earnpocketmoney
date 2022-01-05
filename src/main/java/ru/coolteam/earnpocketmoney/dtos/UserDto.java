package ru.coolteam.earnpocketmoney.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.User;

@Data
@NoArgsConstructor
public class UserDto {

 //   private Long id;
    private String login;
    private String password;
    private String username;
    private RoleDto roleDto;
    private WalletDto walletDto;
    private PeopleGroupsDto peopleGroupsDto;

    public UserDto(User user) {
  //      this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.roleDto = new RoleDto(user.getRole());
        this.walletDto = new WalletDto(user.getWallet());
        this.peopleGroupsDto = new PeopleGroupsDto(user.getPeopleGroups());
    }
}
