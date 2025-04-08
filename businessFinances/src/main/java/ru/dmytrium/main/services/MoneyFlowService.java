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

@Service
public class MoneyFlowService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InOutService inOutService;

    public List<MoneyFlow> buildMoneyFlowReport(Business business, Date start, Date end) {
        List<MoneyFlow> moneyFlowReport = new ArrayList<>();

        List<Object[]> results = transactionRepository.getTotalAmountGroupedByCategory(business, start, end);
        for (Object[] objects : results) {
            MoneyFlow moneyFlow = new MoneyFlow();

            moneyFlow.setCategory((TransactionCategory) objects[0]);
            if (inOutService.isIncome(moneyFlow.getCategory().getType())) {
                moneyFlow.setAmountSum((BigDecimal) objects[1]);
            } else {
                moneyFlow.setAmountSum(((BigDecimal) objects[1]).multiply(new BigDecimal(-1)));
            }

            moneyFlowReport.add(moneyFlow);
        }

        return moneyFlowReport;
    }

}
