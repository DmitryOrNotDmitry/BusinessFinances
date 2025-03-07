package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.Transaction;
import ru.dmytrium.main.repo.TransactionRepository;

@Controller
@RequestMapping("/businesses/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping
    public String transactionCreatePage(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transactionCreate";
    }

    @PostMapping
    public String transactionCreate(@ModelAttribute Transaction transaction,
                                    @SessionAttribute(name = "selectedBusiness") Business selectedBusiness) {

        transaction.setBusiness(selectedBusiness);
        transactionRepository.save(transaction);

        return "redirect:/businesses/transaction";
    }

}
