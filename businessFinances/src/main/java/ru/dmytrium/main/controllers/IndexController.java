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

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
