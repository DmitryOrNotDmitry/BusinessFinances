package ru.dmytrium.main.lab4.req3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyTransactionReportService {
    @Autowired
    private MonthlyTransactionReportRepository reportRepository;

    public List<MonthlyTransactionReport> getMonthlyReport(Long businessId) {
        List<Object[]> results = reportRepository.getMonthlyReportByBusiness(businessId);
        return results.stream()
                .map(row -> new MonthlyTransactionReport(
                        ((Number) row[0]).intValue(), // year
                        ((Number) row[1]).intValue(), // month
                        ((Number) row[2]).longValue(), // total_transactions
                        (BigDecimal) row[3], // avg_amount
                        (BigDecimal) row[4]  // max_transaction
                ))
                .collect(Collectors.toList());
    }
}

