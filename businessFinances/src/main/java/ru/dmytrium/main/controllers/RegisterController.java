package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.entity.form.RegistrationForm;
import ru.dmytrium.main.repo.UserRepository;

import java.util.Objects;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String register(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping
    public String registerConfirm(@ModelAttribute RegistrationForm registrationForm) {
        if (!Objects.equals(registrationForm.getPassword(), registrationForm.getRepeatedPassword())) {
            return "register";
        }

        User newUser = new User();
        newUser.setName(registrationForm.getLogin());
        newUser.setPassword(registrationForm.getPassword());
        newUser.setEmail(registrationForm.getEmail());

        userRepository.save(newUser);
        return "redirect:/login";
    }

}
