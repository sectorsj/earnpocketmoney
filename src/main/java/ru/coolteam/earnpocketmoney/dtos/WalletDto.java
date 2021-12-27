package ru.coolteam.earnpocketmoney.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coolteam.earnpocketmoney.models.Wallet;


@Data
@NoArgsConstructor
public class WalletDto {

 //   private Long id;
    private Long value;

    public WalletDto(Wallet wallet) {
//        this.id = wallet.getId();
        this.value = wallet.getValue();
    }
}
