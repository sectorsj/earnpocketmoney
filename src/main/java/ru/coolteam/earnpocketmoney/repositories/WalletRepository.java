package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository <Wallet, Long> {



}
