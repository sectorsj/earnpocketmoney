package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Role;

@Repository
<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/repositories/ParentRepository.java
public interface ParentRepository extends JpaRepository<Parent, Long> {
=======
//public interface RoleRepository extends JpaRepository<Role, Integer> {
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/repositories/RoleRepository.java
}

