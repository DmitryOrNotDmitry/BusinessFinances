package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.repo.BusinessRepository;

import java.util.List;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @GetMapping
    public String businessPage(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        List<Business> userBusinesses = businessRepository.findAllByAuthor_UserId(userId);
        model.addAttribute("businesses", userBusinesses);
        return "businesses";
    }

    @GetMapping("/new")
    public String businessCreatePage(Model model) {
        model.addAttribute("newBusiness", new Business());
        return "businessCreate";
    }

    @PostMapping()
    public String businessCreateConfirm(@ModelAttribute Business newBusiness,
                                        @SessionAttribute(name = "userId", required = false) Long userId) {
        System.out.println(newBusiness);

        User author = new User();
        author.setUserId(userId);
        newBusiness.setAuthor(author);
        businessRepository.save(newBusiness);

        return "redirect:/businesses";
    }

//    @GetMapping("/{id}")
//    public String businessInfo(Long id, Model model) {
//        return "businessCreate";
//    }

}
