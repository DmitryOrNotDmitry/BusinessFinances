package ru.dmytrium.main.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InvolveBusiness;
import ru.dmytrium.main.entity.Role;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.entity.form.AddRoleForm;
import ru.dmytrium.main.repo.BusinessRepository;
import ru.dmytrium.main.repo.InvolveBusinessRepository;
import ru.dmytrium.main.repo.RoleRepository;
import ru.dmytrium.main.services.RoleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/businesses")
@SessionAttributes("selectedBusiness")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private InvolveBusinessRepository involvesRepository;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String businessPage(@AuthenticationPrincipal User user, Model model) {
        List<InvolveBusiness> allUserRoles = involvesRepository.findAllByUser(user);
        model.addAttribute("userInvolves", allUserRoles);
        return "businesses";
    }

    @GetMapping("/new")
    public String businessCreatePage(Model model) {
        model.addAttribute("newBusiness", new Business());
        return "business/businessCreate";
    }

    @PostMapping()
    public String businessCreateConfirm(@ModelAttribute Business newBusiness,
                                        @AuthenticationPrincipal User author) {

        newBusiness.setAuthor(author);
        businessRepository.save(newBusiness);

        InvolveBusiness newInvolve = new InvolveBusiness();
        newInvolve.setBusiness(newBusiness);
        newInvolve.setRole(roleService.getOwnerRole());
        newInvolve.setUser(author);
        involvesRepository.save(newInvolve);

        return "redirect:/businesses";
    }

    @GetMapping(value = {"/{businessId}", "/{businessId}/settings"})
    public String businessInfo(@PathVariable Long businessId, Model model) {
        Optional<Business> business = businessRepository.findById(businessId);

        if (business.isEmpty()) {
            return "redirect:/businesses";
        }

        model.addAttribute("selectedBusiness", business.get());

        Business copyOfBusiness = business.get().clone();
        model.addAttribute("newBusinessSettings", copyOfBusiness);

        return "business/businessInfo";
    }

    @PostMapping("/{businessId}/settings")
    public String businessEdit(@PathVariable Long businessId,
                               @ModelAttribute Business newBusinessSettings,
                               @SessionAttribute(name = "selectedBusiness") Business business) {

        if (!business.getBusinessName().equals(newBusinessSettings.getBusinessName())) {
            business.setBusinessName(newBusinessSettings.getBusinessName());
            businessRepository.save(business);
        }

        return String.format("redirect:/businesses/%d", businessId);
    }

    @PostMapping("/{businessId}/delete")
    public String businessDelete(@SessionAttribute(name = "selectedBusiness") Business business,
                                 HttpSession session, SessionStatus sessionStatus) {
        businessRepository.delete(business);
        session.removeAttribute("selectedBusiness");
        sessionStatus.setComplete();

        return "redirect:/businesses";
    }


}
