package ru.coolteam.earnpocketmoney.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coolteam.earnpocketmoney.models.Wallet;
import ru.coolteam.earnpocketmoney.repositories.WalletRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private  final WalletRepository walletRepository;

    public Wallet saveWallet (Wallet wallet){
        return walletRepository.save(wallet);
    }



}
