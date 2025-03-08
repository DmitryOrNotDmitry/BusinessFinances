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
import ru.dmytrium.main.repo.InvolveBusinessRepository;
import ru.dmytrium.main.repo.RoleRepository;
import ru.dmytrium.main.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/businesses/{businessId}/participant")
public class InvolveController {

    @Autowired
    private InvolveBusinessRepository involveRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String usersAndRoles(@PathVariable Long businessId,
                                @SessionAttribute(name = "selectedBusiness") Business business,
                                Model model) {

        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("roles", allRoles);

        model.addAttribute("addRoleTraits", new AddRoleForm());

        List<InvolveBusiness> participants = involveRepository.findAllByBusiness(business);
        model.addAttribute("participants", participants);

        model.addAttribute("selectedBusiness", business);

        return "business/businessInvolves";
    }

    @PostMapping
    public String addParticipant(@PathVariable Long businessId,
                                 @ModelAttribute(name = "addRoleTraits") AddRoleForm addRoleTraits,
                                 @SessionAttribute(name = "selectedBusiness") Business selectedBusiness) {

        Optional<User> newParticipant = userRepository.findByName(addRoleTraits.getLogin());
        if (newParticipant.isEmpty()) {
            return String.format("redirect:/businesses/%d/participant", businessId);
        }

        Optional<InvolveBusiness> alreadyExistsInvolve =
                involveRepository.findByBusinessAndUser(selectedBusiness, newParticipant.get());

        if (alreadyExistsInvolve.isPresent()) {
            alreadyExistsInvolve.get().setRole(addRoleTraits.getRole());

            involveRepository.save(alreadyExistsInvolve.get());
        }
        else {
            InvolveBusiness newInvolveBusiness = new InvolveBusiness();
            newInvolveBusiness.setBusiness(new Business(businessId));
            newInvolveBusiness.setUser(newParticipant.get());
            newInvolveBusiness.setRole(addRoleTraits.getRole());

            involveRepository.save(newInvolveBusiness);
        }

        return String.format("redirect:/businesses/%d/participant", businessId);
    }

    @PostMapping("/delete")
    public String deleteParticipant(@PathVariable Long businessId,
                                    @RequestParam Long involveCode) {

        involveRepository.deleteById(involveCode);

        return String.format("redirect:/businesses/%d/participant", businessId);
    }

}
