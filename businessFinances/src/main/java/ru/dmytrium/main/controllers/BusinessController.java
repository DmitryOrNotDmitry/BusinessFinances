package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InvolveBusiness;
import ru.dmytrium.main.entity.Role;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.entity.form.AddRoleForm;
import ru.dmytrium.main.repo.BusinessRepository;
import ru.dmytrium.main.repo.InvolveBusinessRepository;
import ru.dmytrium.main.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/businesses")
@SessionAttributes("selectedBusiness")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private InvolveBusinessRepository involvesRepository;

    @GetMapping
    public String businessPage(@SessionAttribute(name = "user", required = false) User user, Model model) {
        List<Business> userBusinesses = businessRepository.findAllByAuthor(user);
        model.addAttribute("businesses", userBusinesses);
        return "businesses";
    }

    @GetMapping("/new")
    public String businessCreatePage(Model model) {
        model.addAttribute("newBusiness", new Business());
        return "businessCreate";
    }

    @PostMapping()
    public String businessCreateConfirm(@ModelAttribute Business newBusiness,
                                        @SessionAttribute(name = "user", required = false) User author) {
        newBusiness.setAuthor(author);
        businessRepository.save(newBusiness);

        return "redirect:/businesses";
    }

    @GetMapping("/{businessId}")
    public String businessInfo(@PathVariable Long businessId, Model model) {
        Optional<Business> business = businessRepository.findById(businessId);

        if (business.isEmpty()) {
            return "businesses";
        }

        model.addAttribute("business", business.get());
        model.addAttribute("selectedBusiness", business.get());

        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("roles", allRoles);

        model.addAttribute("addRoleTraits", new AddRoleForm());

        List<InvolveBusiness> participants = involvesRepository.findAllByBusiness(business.get());
        model.addAttribute("participants", participants);

        return "businessInfo";
    }

}
