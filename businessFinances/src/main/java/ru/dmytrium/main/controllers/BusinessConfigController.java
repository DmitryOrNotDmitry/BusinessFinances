package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.*;
import ru.dmytrium.main.repo.AccountRepository;
import ru.dmytrium.main.repo.AgentRepository;
import ru.dmytrium.main.repo.TransactionCategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/businesses/{businessId}/config")
public class BusinessConfigController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionCategoryRepository categoryRepository;

    @GetMapping()
    public String businessConfigPage(@SessionAttribute(name = "selectedBusiness") Business business,
                                     Model model) {
        List<Agent> agents = agentRepository.findAllByForBusiness(business);
        model.addAttribute("agents", agents);

        List<Account> accounts = accountRepository.findAllByForBusiness(business);
        model.addAttribute("accounts", accounts);

        List<TransactionCategory> categories = categoryRepository.findAllByForBusiness(business);
        model.addAttribute("categories", categories);

        Agent newAgent = new Agent();
        model.addAttribute("newAgent", newAgent);

        Account newAccount = new Account();
        model.addAttribute("newAccount", newAccount);

        TransactionCategory newCategory = new TransactionCategory();
        model.addAttribute("newCategory", newCategory);

        model.addAttribute("selectedBusiness", business);

        return "business/businessConfig";
    }

    @PostMapping("/agent")
    public String createAgent(@ModelAttribute Agent newAgent,
                              @SessionAttribute(name = "selectedBusiness") Business business,
                              @SessionAttribute(name = "user") User author) {

        if (!newAgent.getAgentName().isBlank()) {
            newAgent.setAuthor(author);
            newAgent.setForBusiness(business);
            agentRepository.save(newAgent);
        }

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }

    @PostMapping("/agent/delete")
    public String deleteAgent(@RequestParam Long agentId,
                              @SessionAttribute(name = "selectedBusiness") Business business) {

        agentRepository.deleteById(agentId);

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }


    @PostMapping("/account")
    public String createAccount(@ModelAttribute Account newAccount,
                                @SessionAttribute(name = "selectedBusiness") Business business,
                                @SessionAttribute(name = "user") User author) {

        if (!newAccount.getAccountName().isBlank()) {
            newAccount.setAuthor(author);
            newAccount.setForBusiness(business);
            accountRepository.save(newAccount);
        }

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }

    @PostMapping("/account/delete")
    public String deleteAccount(@RequestParam Long accountCode,
                                @SessionAttribute(name = "selectedBusiness") Business business) {

        accountRepository.deleteById(accountCode);

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }

    @PostMapping("/category")
    public String createCategory(@ModelAttribute TransactionCategory newCategory,
                                 @SessionAttribute(name = "selectedBusiness") Business business,
                                 @SessionAttribute(name = "user") User author) {

        if (!newCategory.getCategoryName().isBlank()) {
            newCategory.setAuthor(author);
            newCategory.setForBusiness(business);
            categoryRepository.save(newCategory);
        }

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam Long categoryId,
                                @SessionAttribute(name = "selectedBusiness") Business business) {

        categoryRepository.deleteById(categoryId);

        return String.format("redirect:/businesses/%d/config", business.getBusinessId());
    }

}
