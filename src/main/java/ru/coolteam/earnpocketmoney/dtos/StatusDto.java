package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Status;

@Data
@NoArgsConstructor
public class StatusDto {

 //   private Long id;
    private String title;

    public StatusDto(Status status) {
 //       this.id = status.getId();
        this.title = status.getTitle();
    }
}
