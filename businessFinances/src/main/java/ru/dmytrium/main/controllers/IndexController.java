package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dmytrium.main.entity.Agent;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.repo.AgentRepository;
import ru.dmytrium.main.repo.BusinessRepository;
import ru.dmytrium.main.repo.ConsideringRepository;
import ru.dmytrium.main.repo.UserRepository;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ConsideringRepository consideringRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @GetMapping("/")
    public String index() {
        System.out.println(userRepository.findAll().size());
        System.out.println(agentRepository.findAll().size());
        System.out.println(consideringRepository.findAll().size());
        for (Business b : businessRepository.findAll()) {
            System.out.println(b.getAuthor());
        }
        return "index";
    }

}
