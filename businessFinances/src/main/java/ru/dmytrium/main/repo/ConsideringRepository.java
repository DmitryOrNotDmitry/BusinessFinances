package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Considering;
import ru.dmytrium.main.entity.ConsideringId;
import ru.dmytrium.main.entity.Obligation;

import java.util.List;

@Repository
public interface ConsideringRepository extends JpaRepository<Considering, ConsideringId> {
    @Query("""
        SELECT c FROM Considering c
        WHERE c.obligation = :obligation
    """)
    List<Considering> findAllByObligation(Obligation obligation);
}

