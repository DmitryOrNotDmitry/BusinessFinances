package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.*;
import ru.dmytrium.main.repo.AccountRepository;
import ru.dmytrium.main.repo.AgentRepository;
import ru.dmytrium.main.repo.TransactionCategoryRepository;
import ru.dmytrium.main.repo.TransactionRepository;

import java.util.List;

@Controller
@RequestMapping("/businesses/{businessId}/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionCategoryRepository categoryRepository;

    @GetMapping
    public String transactionCreatePage(@SessionAttribute(name = "selectedBusiness") Business business,
                                        Model model) {
        model.addAttribute("newTransaction", new Transaction());

        List<Transaction> transactions = transactionRepository
                .findRecentTransactionsByBusiness(business, PageRequest.of(0, 20));
        model.addAttribute("transactions", transactions);

        List<Agent> agents = agentRepository.findAllByForBusiness(business);
        model.addAttribute("agents", agents);

        List<Account> accounts = accountRepository.findAllByForBusiness(business);
        model.addAttribute("accounts", accounts);

        List<TransactionCategory> categories = categoryRepository.findAllByForBusiness(business);
        model.addAttribute("categories", categories);

        model.addAttribute("selectedBusiness", business);

        return "business/transactionCreate";
    }

    @PostMapping
    public String transactionCreate(@ModelAttribute Transaction newTransaction,
                                    @SessionAttribute(name = "selectedBusiness") Business selectedBusiness) {

        newTransaction.setBusiness(selectedBusiness);
        transactionRepository.save(newTransaction);

        return String.format("redirect:/businesses/%d/transaction", selectedBusiness.getBusinessId());
    }

}
