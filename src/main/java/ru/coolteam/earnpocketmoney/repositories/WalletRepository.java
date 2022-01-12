package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Wallet;

@Repository
<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/repositories/ChildRepository.java
public interface ChildRepository extends JpaRepository<Child, Long> {
=======
public interface WalletRepository extends JpaRepository <Wallet, Long> {



>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/repositories/WalletRepository.java
}
