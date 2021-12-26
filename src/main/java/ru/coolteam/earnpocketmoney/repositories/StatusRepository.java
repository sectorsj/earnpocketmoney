package ru.coolteam.earnpocketmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coolteam.earnpocketmoney.models.Status;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findById(Long id);
    Optional<Status> findByTitle(String title);

}
