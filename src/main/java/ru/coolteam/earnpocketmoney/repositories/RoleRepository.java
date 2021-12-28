package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Role;

@Repository
//public interface RoleRepository extends JpaRepository<Role, Integer> {
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}

