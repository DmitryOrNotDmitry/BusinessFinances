package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByBusinessName(String businessName);
}

