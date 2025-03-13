package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.entity.form.UserTraitsForm;
import ru.dmytrium.main.repo.UserRepository;

@Controller
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String profilePage(@AuthenticationPrincipal User user,
                              @SessionAttribute(name = "selectedBusiness", required = false) Business business,
                              Model model) {
        model.addAttribute("newUserTraits",
                new UserTraitsForm(user.getName(), user.getEmail())
        );

        model.addAttribute("selectedBusiness", business);

        return "profile";
    }

    @PostMapping
    public String profileEdit(@AuthenticationPrincipal User user,
                              @ModelAttribute UserTraitsForm newUserTraits) {
        user.setName(newUserTraits.getNewName());
        user.setEmail(newUserTraits.getNewEmail());

        if (user.getPassword().equals(newUserTraits.getOldPassword())
        && newUserTraits.getNewPassword().equals(newUserTraits.getRepeatedNewPassword())) {
            user.setPassword(newUserTraits.getNewPassword());
        }

        userRepository.save(user);

        return "redirect:/profile";
    }

}
