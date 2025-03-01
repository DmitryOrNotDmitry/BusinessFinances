package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Obligation;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {
}

