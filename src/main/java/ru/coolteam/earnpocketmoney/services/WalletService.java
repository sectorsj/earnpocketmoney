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

<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/services/ParentService.java
    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
=======
    public Wallet saveWallet (Wallet wallet){
        return walletRepository.save(wallet);
>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/services/WalletService.java
    }


}
