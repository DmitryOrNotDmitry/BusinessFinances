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
import ru.dmytrium.main.entity.reports.Metric;
import ru.dmytrium.main.services.MetricsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/businesses/{businessId}/metrics")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping
    public String getMetricPage(@SessionAttribute(name = "selectedBusiness") Business business,
                                @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                Model model) {

        List<Metric> metrics = new ArrayList<>();
        if (startDate != null && endDate != null) {
            metrics = metricsService.getAllMetrics(business, startDate, endDate);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            model.addAttribute("startDate", sdf.format(startDate));
            model.addAttribute("endDate", sdf.format(endDate));
        }
        model.addAttribute("metrics", metrics);

        model.addAttribute("selectedBusiness", business);

        return "report/metricsTable";
    }

}
