package ru.dmytrium.main.lab5;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lab5")
public class ReportController {

    @Autowired
    private Birt birt;

    @GetMapping
    public String report() {
        return "lab5/report";
    }

    @PostMapping("/users_report")
    public void usersReport(@RequestParam String id, HttpServletResponse response, HttpServletRequest request) {
        birt.generateUsersReport(id, response, request);
    }

    @PostMapping("/trans_report")
    public void transReport(@RequestParam String businessId, HttpServletResponse response, HttpServletRequest request) {
        birt.generateTransactionsReport(businessId, response, request);
    }
}
