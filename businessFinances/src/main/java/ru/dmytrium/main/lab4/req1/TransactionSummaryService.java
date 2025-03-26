package ru.dmytrium.main.lab4.req1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionSummaryService {

    @Autowired
    private Lab4TransactionRepository transactionRepository;

    public TransactionSummary getTransactionSummary(Long businessId) {
        List<Object[]> results = transactionRepository.getTransactionSummaryByBusiness(businessId);
        if (results.isEmpty()) {
            return new TransactionSummary(0L, BigDecimal.ZERO, BigDecimal.ZERO, null, null);
        }

        Object[] row = results.getFirst();
        return new TransactionSummary(
                (Long) row[0],
                (BigDecimal) row[1],
                (BigDecimal) row[2],
                (Date) row[3],
                (Date) row[4]
        );
    }

}
