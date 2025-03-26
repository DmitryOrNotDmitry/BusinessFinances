package ru.dmytrium.main.lab4.req3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Transaction;

import java.util.List;

@Repository
public interface MonthlyTransactionReportRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM get_monthly_transactions_report(:businessId)", nativeQuery = true)
    List<Object[]> getMonthlyReportByBusiness(@Param("businessId") Long businessId);
}

