package ru.dmytrium.main.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.business = :business ORDER BY t.transactionDate DESC")
    List<Transaction> findRecentTransactionsByBusiness(Business business, Pageable pageable);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t " +
            "WHERE t.business = :business " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate " +
            "GROUP BY t.category")
    List<Object[]> getTotalAmountGroupedByCategory(
            @Param("business") Business business,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}

