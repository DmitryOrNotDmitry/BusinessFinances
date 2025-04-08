package ru.dmytrium.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Obligation;
import ru.dmytrium.main.entity.reports.Metric;
import ru.dmytrium.main.repo.ObligationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MetricsService {

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private InOutService inOutService;

    public List<Metric> getAllMetrics(Business business, Date start, Date end) {
        List<Metric> metrics = new ArrayList<>();

        List<Obligation> obligations = obligationRepository.findAllByBusinessAndDates(business, start, end);

        metrics.add(getIncomeMetric(obligations));
        metrics.add(getOutcomeMetric(obligations));
        metrics.add(getProfitMetric(metrics.get(0), metrics.get(1)));

        return metrics;
    }

    private Metric getIncomeMetric(List<Obligation> obligations) {
        Metric metric = new Metric();

        metric.setName("Доход");

        BigDecimal positiveSum = BigDecimal.ZERO;
        for (Obligation obligation : obligations) {
            if (inOutService.isIncome(obligation.getType())) {
                positiveSum = positiveSum.add(obligation.getAmount());
            }
        }
        metric.setValue(positiveSum);

        return metric;
    }

    private Metric getOutcomeMetric(List<Obligation> obligations) {
        Metric metric = new Metric();

        metric.setName("Расход");

        BigDecimal negativeSum = BigDecimal.ZERO;
        for (Obligation obligation : obligations) {
            if (inOutService.isExpense(obligation.getType())) {
                negativeSum = negativeSum.add(obligation.getAmount().multiply(BigDecimal.valueOf(-1)));
            }
        }
        metric.setValue(negativeSum);

        return metric;
    }

    private Metric getProfitMetric(Metric in, Metric out) {
        Metric metric = new Metric();

        metric.setName("Прибыль");
        metric.setValue(in.getValue().add(out.getValue()));

        return metric;
    }

}
