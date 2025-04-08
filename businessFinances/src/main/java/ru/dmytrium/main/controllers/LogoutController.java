package ru.dmytrium.main.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logoutConfirm(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
