package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.coolteam.earnpocketmoney.models.Bonus;
import ru.coolteam.earnpocketmoney.models.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BonusDto {

    private Integer id;
    private String title;
    private String bonusText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto userCreatingBonus;
    private UserDto userGettingBonus;
    private Long price;
    private Boolean gettingStatus;

    public BonusDto(Bonus bonus) {
        this.id = bonus.getId();
        this.title = bonus.getTitle();
        this.bonusText = bonus.getBonusText();
        this.createdAt = bonus.getCreatedAt();
        this.updatedAt = bonus.getUpdatedAt();
        this.userCreatingBonus = new UserDto(bonus.getUserCreatingBonus());
        if(bonus.getUserGettingBonus()!=null){
        this.userGettingBonus = new UserDto(bonus.getUserGettingBonus());
        }else {
            this.userGettingBonus = new UserDto();
        }
        this.price = bonus.getPrice();
        this.gettingStatus = bonus.getGettingStatus();
    }
}
