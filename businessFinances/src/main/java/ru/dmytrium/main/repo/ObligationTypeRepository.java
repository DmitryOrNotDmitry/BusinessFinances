package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.ObligationType;

@Repository
public interface ObligationTypeRepository extends JpaRepository<ObligationType, String> {
}

