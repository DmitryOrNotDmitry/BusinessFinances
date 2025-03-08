package ru.dmytrium.main.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dmytrium.main.entity.User;

import java.util.Optional;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logoutConfirm(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
