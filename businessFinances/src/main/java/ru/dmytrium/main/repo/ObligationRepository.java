package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Obligation;

import java.util.Date;
import java.util.List;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {
    @Query("""
        SELECT o FROM Obligation o
        WHERE o.business = :business
    """)
    List<Obligation> findAllByBusiness(Business business);

    @Query("""
        SELECT o FROM Obligation o
        WHERE o.business = :business
        AND o.dueDate BETWEEN :startDate AND :endDate
    """)
    List<Obligation> findAllByBusinessAndDates(Business business, Date startDate, Date endDate);
}

