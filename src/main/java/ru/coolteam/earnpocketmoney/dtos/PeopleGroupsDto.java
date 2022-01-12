package ru.coolteam.earnpocketmoney.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;

@Data
@NoArgsConstructor
public class PeopleGroupsDto {

    // private Long id;
    private String name;

    public PeopleGroupsDto(PeopleGroups peopleGroups) {
        //      this.id = family.getId();
        this.name = peopleGroups.getName();
    }
}