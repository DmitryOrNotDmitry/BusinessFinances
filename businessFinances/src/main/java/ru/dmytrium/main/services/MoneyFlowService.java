package ru.dmytrium.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.TransactionCategory;
import ru.dmytrium.main.entity.reports.MoneyFlow;
import ru.dmytrium.main.repo.TransactionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MoneyFlowService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<MoneyFlow> buildMoneyFlowReport(Business business, Date start, Date end) {
        List<MoneyFlow> moneyFlowReport = new ArrayList<>();

        List<Object[]> results = transactionRepository.getTotalAmountGroupedByCategory(business, start, end);
        for (Object[] objects : results) {
            MoneyFlow moneyFlow = new MoneyFlow();

            moneyFlow.setCategory((TransactionCategory) objects[0]);
            moneyFlow.setAmountSum((BigDecimal) objects[1]);

            moneyFlowReport.add(moneyFlow);
        }

        return moneyFlowReport;
    }

}
