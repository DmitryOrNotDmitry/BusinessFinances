package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.InOutType;

@Repository
public interface InOutTypeRepository extends JpaRepository<InOutType, String> {
}

