package ru.coolteam.earnpocketmoney.dtos;


import lombok.Data;
<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/dtos/ParentDto.java
import ru.coolteam.earnpocketmoney.models.Parent;

@Data
public class ParentDto {
    private String login;
    private String password;
=======
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;

@Data
@NoArgsConstructor
public class PeopleGroupsDto {

   // private Long id;
    private String name;
>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/dtos/PeopleGroupsDto.java

    public PeopleGroupsDto(PeopleGroups peopleGroups) {
  //      this.id = family.getId();
        this.name = peopleGroups.getName();
    }
}
