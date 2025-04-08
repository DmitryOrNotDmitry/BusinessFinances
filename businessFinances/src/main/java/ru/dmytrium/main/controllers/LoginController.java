package ru.dmytrium.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmytrium.main.entity.User;

@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping
    public String login(Model model) {
        User user = new User();
        user.setName("user"); // TODO
        user.setPassword("1");
        model.addAttribute("user_login", user);
        return "login";
    }

//    @PostMapping
//    public String loginConfirm(@ModelAttribute(name = "user_login") User userLogin, Model model) {
//        System.out.println("UnReachable");
//        Optional<User> userFromDB = userRepository.findByName(userLogin.getName());
//
//        if (userFromDB.isEmpty()) {
//            return "login";
//        }
//
//        if (!userFromDB.get().getPassword().equals(userLogin.getPassword())) {
//            return "login";
//        }
//
//        model.addAttribute("user", userFromDB.get());
//
//        return "redirect:/businesses";
//    }

}
