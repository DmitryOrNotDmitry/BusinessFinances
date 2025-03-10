package ru.dmytrium.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Obligation;
import ru.dmytrium.main.entity.reports.Metric;
import ru.dmytrium.main.repo.ConsideringRepository;
import ru.dmytrium.main.repo.ObligationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MetricsService {

    @Autowired
    private ConsideringRepository consideringRepository;

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private ObligationService obligationService;

    public List<Metric> getAllMetrics(Business business, Date start, Date end) {
        List<Metric> metrics = new ArrayList<>();

        List<Obligation> obligations = obligationRepository.findAllByBusinessAndDates(business, start, end);

        Map<Obligation, BigDecimal> amountsSum = obligationService.calcAmountsSum(obligations);

        metrics.add(getIncomeMetric(amountsSum));
        metrics.add(getOutcomeMetric(amountsSum));
        metrics.add(getProfitMetric(metrics.get(0), metrics.get(1)));

        return metrics;
    }

    private Metric getIncomeMetric(Map<Obligation, BigDecimal> amountsSum) {
        Metric metric = new Metric();

        metric.setName("Доход");

        BigDecimal positiveSum = BigDecimal.ZERO;
        for (Map.Entry<Obligation, BigDecimal> entry : amountsSum.entrySet()) {
            if (entry.getValue().compareTo(BigDecimal.ZERO) >= 0) {
                positiveSum = positiveSum.add(entry.getValue());
            }
        }
        metric.setValue(positiveSum);

        return metric;
    }

    private Metric getOutcomeMetric(Map<Obligation, BigDecimal> amountsSum) {
        Metric metric = new Metric();

        metric.setName("Расход");

        BigDecimal negativeSum = BigDecimal.ZERO;
        for (Map.Entry<Obligation, BigDecimal> entry : amountsSum.entrySet()) {
            if (entry.getValue().compareTo(BigDecimal.ZERO) < 0) {
                negativeSum = negativeSum.add(entry.getValue());
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
