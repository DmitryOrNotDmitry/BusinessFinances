package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.InvolveBusiness;

@Repository
public interface InvolveBusinessRepository extends JpaRepository<InvolveBusiness, Long> {
}
