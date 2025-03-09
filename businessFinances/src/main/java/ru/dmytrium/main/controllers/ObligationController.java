package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.*;
import ru.dmytrium.main.entity.form.ObligationForm;
import ru.dmytrium.main.repo.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/businesses/{businessId}/obligation")
public class ObligationController {

    @Autowired
    ObligationRepository obligationRepository;

    @Autowired
    ObligationTypeRepository obligationTypeRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ConsideringRepository consideringRepository;

    private Map<Obligation, BigDecimal> calcAmountsSum(List<Obligation> obligations) {
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

    private Map<Obligation, String> calcTransactionsToString(List<Obligation> obligations) {
        Map<Obligation, String> strings = new HashMap<>();

        for (Obligation o : obligations) {
            List<Considering> considerings = consideringRepository.findAllByObligation(o);
            StringBuilder s = new StringBuilder();
            for (Considering c : considerings) {
                s.append(c.getTransaction().getDescription())
                        .append(", ");
            }

            if (!s.isEmpty()) {
                s.deleteCharAt(s.length() - 1);
                s.deleteCharAt(s.length() - 1);
            }

            strings.put(o, s.toString());
        }

        return strings;
    }

    @GetMapping
    public String obligationCreatePage(@SessionAttribute(name = "selectedBusiness") Business business,
                                        Model model) {
        model.addAttribute("obligationForm", new ObligationForm());

        List<Obligation> obligations = obligationRepository.findAllByBusiness(business);
        model.addAttribute("obligations", obligations);

        List<Transaction> transactions = transactionRepository
                .findRecentTransactionsByBusiness(business, PageRequest.of(0, 20));
        model.addAttribute("transactions", transactions);

        List<ObligationType> obligationTypes = obligationTypeRepository.findAll();
        model.addAttribute("types", obligationTypes);

        model.addAttribute("amountsSum", calcAmountsSum(obligations));
        model.addAttribute("transactionsString", calcTransactionsToString(obligations));

        model.addAttribute("selectedBusiness", business);

        return "business/obligationCreate";
    }

    @PostMapping
    public String obligationCreate(@ModelAttribute ObligationForm obligationForm,
                                   @SessionAttribute(name = "selectedBusiness") Business selectedBusiness) {

        Obligation obligation = new Obligation();
        obligation.setDescription(obligationForm.getDescription());
        obligation.setType(obligationForm.getType());
        obligation.setDueDate(obligationForm.getDueDate());
        obligation = obligationRepository.save(obligation);

        for (Transaction t : obligationForm.getTransactions()) {
            Considering considering = new Considering();
            considering.setObligation(obligation);
            considering.setTransaction(t);
            consideringRepository.save(considering);
        }

        return String.format("redirect:/businesses/%d/obligation", selectedBusiness.getBusinessId());
    }

    @PostMapping("/delete")
    public String deleteObligation(@RequestParam Long obligationId,
                              @SessionAttribute(name = "selectedBusiness") Business business) {

        obligationRepository.deleteById(obligationId);

        return String.format("redirect:/businesses/%d/obligation", business.getBusinessId());
    }

}
