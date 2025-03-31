package ru.dmytrium.main.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> result = lab3UserRepository.findSmthUsers(sql);
        model.addAttribute("resultTable", result);
        return "lab3/statement";
    }


}
