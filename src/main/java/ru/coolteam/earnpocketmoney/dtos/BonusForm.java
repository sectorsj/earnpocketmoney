package ru.coolteam.earnpocketmoney.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BonusForm {

    private Integer id;
    private String title;
    private String bonusText;
    private String userCreatingBonus;
    private String userGettingBonus;
    private Long price;


}
