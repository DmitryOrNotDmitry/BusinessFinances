package ru.dmytrium.main.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmytrium.main.lab4.req1.TransactionSummaryService;
import ru.dmytrium.main.lab4.req2.TopAgentService;
import ru.dmytrium.main.lab4.req3.MonthlyTransactionReportService;

@Controller
@RequestMapping("/lab4")
public class Lab4Controller {

    @Autowired
    private TransactionSummaryService transactionService;

    @Autowired
    private TopAgentService topAgentService;

    @Autowired
    private MonthlyTransactionReportService reportService;

    @GetMapping
    public String getIndex() {
        return "lab4/index";
    }

    @GetMapping("/t_summary/{businessId}")
    public String getSummary(@PathVariable Long businessId, Model model) {
        model.addAttribute("t_summary", transactionService.getTransactionSummary(businessId));
        return "lab4/t_summary";
    }


    @GetMapping("/top_agents/{businessId}")
    public String getTopAgents(@PathVariable Long businessId, Model model) {
        model.addAttribute("top_agents", topAgentService.getTopAgents(businessId));
        return "lab4/top_agents";
    }

    @GetMapping("/monthly_report/{businessId}")
    public String getMonthlyReport(@PathVariable Long businessId, Model model) {
        model.addAttribute("monthly_reports", reportService.getMonthlyReport(businessId));
        return "lab4/monthly_report";
    }
    
}
