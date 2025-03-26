package ru.dmytrium.main.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lab3")
public class Lab3Controller {

    @Autowired
    private Lab3UserRepository lab3UserRepository;

    @GetMapping
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lab3/statement");
        modelAndView.addObject("users", lab3UserRepository.getUsers());
        return modelAndView;
    }

    @PostMapping("/findUsers")
    public String findUser(@RequestParam String name, Model model) {
        model.addAttribute("findedUsers", lab3UserRepository.findUsers(name));
        return "lab3/statement";
    }

    @PostMapping("/findSmthUsers")
    public String findSmthUsers(@RequestParam String sql, Model model) {
        model.addAttribute("findedSmthUsers", lab3UserRepository.findSmthUsers(sql));
        return "lab3/statement";
    }


}
