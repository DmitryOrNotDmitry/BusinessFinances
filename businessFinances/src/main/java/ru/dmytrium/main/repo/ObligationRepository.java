package ru.dmytrium.main.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Obligation;
import ru.dmytrium.main.entity.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {
    @Query("""
        SELECT o FROM Obligation o
        JOIN Considering c ON c.obligation = o
        JOIN Transaction t ON c.transaction = t
        WHERE t.business = :business
    """)
    List<Obligation> findAllByBusiness(Business business);

    @Query("""
        SELECT o FROM Obligation o
        JOIN Considering c ON c.obligation = o
        JOIN Transaction t ON c.transaction = t
        WHERE t.business = :business
        AND t.transactionDate BETWEEN :startDate AND :endDate
    """)
    List<Obligation> findAllByBusinessAndDates(Business business, Date startDate, Date endDate);
}

