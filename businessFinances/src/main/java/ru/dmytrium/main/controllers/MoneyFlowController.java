package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.reports.MoneyFlow;
import ru.dmytrium.main.entity.reports.MoneyFlowSummary;
import ru.dmytrium.main.services.MoneyFlowService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/businesses/{businessId}/moneyflow")
public class MoneyFlowController {

    @Autowired
    private MoneyFlowService moneyFlowService;

    @GetMapping
    public String getReportPage(@SessionAttribute(name = "selectedBusiness") Business business,
                                @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                Model model) {

        List<MoneyFlow> moneyFlowList = new ArrayList<>();
        if (startDate != null && endDate != null) {
            moneyFlowList = moneyFlowService.buildMoneyFlowReport(business, startDate, endDate);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            model.addAttribute("startDate", sdf.format(startDate));
            model.addAttribute("endDate", sdf.format(endDate));
        }
        model.addAttribute("moneyFlowReport", moneyFlowList);

        MoneyFlowSummary summary = getMoneyFlowSummary(moneyFlowList);

        model.addAttribute("summaryReport", summary);

        model.addAttribute("selectedBusiness", business);

        return "report/moneyFlowReport";
    }

    private static MoneyFlowSummary getMoneyFlowSummary(List<MoneyFlow> moneyFlowList) {
        MoneyFlowSummary summary = new MoneyFlowSummary();
        BigDecimal sumOfIn = new BigDecimal(0);
        BigDecimal sumOfOut = new BigDecimal(0);
        for (MoneyFlow flowItem : moneyFlowList) {
            if (flowItem.getAmountSum().compareTo(BigDecimal.ZERO) >= 0) {
                sumOfIn = sumOfIn.add(flowItem.getAmountSum());
            } else {
                sumOfOut = sumOfOut.add(flowItem.getAmountSum());
            }
        }
        summary.setInTotal(sumOfIn);
        summary.setOutTotal(sumOfOut);
        summary.setName("Итого");
        return summary;
    }

}
