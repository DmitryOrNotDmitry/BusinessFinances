package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.repo.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user_login", new User());
        return "login";
    }

    @PostMapping
    public String loginConfirm(@ModelAttribute(name = "user_login") User userLogin) {
        Optional<User> userFromDB = userRepository.findByName(userLogin.getName());

        if (userFromDB.isEmpty()) {
            return "login";
        }

        if (!userFromDB.get().getPassword().equals(userLogin.getPassword())) {
            return "login";
        }

        return "redirect:/mainContent";
    }



}
