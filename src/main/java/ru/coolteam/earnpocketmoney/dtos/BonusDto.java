package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Bonus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BonusDto {

    private String title;
    private ParentDto parentDto;
    private ChildDto childDto;
    private Integer price;
    private LocalDateTime receivedAt;

    public BonusDto(Bonus bonus) {
        this.title = bonus.getTitle();
        this.parentDto = new ParentDto(bonus.getParent());
        if(bonus.getChild()!=null) {
            this.childDto = new ChildDto(bonus.getChild());
        }else {
            this.childDto = new ChildDto();
        }
        this.price = bonus.getPrice();
        this.receivedAt = bonus.getReceivedAt();
    }



}
