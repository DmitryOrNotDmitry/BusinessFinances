package ru.dmytrium.main.lab4.req1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Transaction;

import java.util.List;

@Repository
public interface Lab4TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM get_transaction_summary_by_business(:businessId)", nativeQuery = true)
    List<Object[]> getTransactionSummaryByBusiness(@Param("businessId") Long businessId);
}

