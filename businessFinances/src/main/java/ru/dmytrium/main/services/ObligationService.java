package ru.dmytrium.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Considering;
import ru.dmytrium.main.entity.Obligation;
import ru.dmytrium.main.repo.ConsideringRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObligationService {

    @Autowired
    ConsideringRepository consideringRepository;

    public Map<Obligation, BigDecimal> calcAmountsSum(List<Obligation> obligations) {
        Map<Obligation, BigDecimal> amountsSum = new HashMap<>();

        for (Obligation o : obligations) {
            List<Considering> considerings = consideringRepository.findAllByObligation(o);
            BigDecimal sum = new BigDecimal(0);
            for (Considering c : considerings) {
                sum = sum.add(c.getTransaction().getAmount());
            }

            amountsSum.put(o, sum);
        }

        return amountsSum;
    }

}
