package ru.dmytrium.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InOutType;
import ru.dmytrium.main.entity.Obligation;
import ru.dmytrium.main.entity.form.ObligationForm;
import ru.dmytrium.main.repo.InOutTypeRepository;
import ru.dmytrium.main.repo.ObligationRepository;

import java.util.List;

@Controller
@RequestMapping("/businesses/{businessId}/obligation")
public class ObligationController {

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private InOutTypeRepository inOutTypeRepository;

    @GetMapping
    public String obligationCreatePage(@SessionAttribute(name = "selectedBusiness") Business business,
                                        Model model) {
        model.addAttribute("obligationForm", new ObligationForm());

        List<Obligation> obligations = obligationRepository.findAllByBusiness(business);
        model.addAttribute("obligations", obligations);

        List<InOutType> obligationTypes = inOutTypeRepository.findAll();
        model.addAttribute("types", obligationTypes);

        model.addAttribute("selectedBusiness", business);

        return "business/obligationCreate";
    }

    @PostMapping
    public String obligationCreate(@ModelAttribute ObligationForm obligationForm,
                                   @SessionAttribute(name = "selectedBusiness") Business selectedBusiness) {

        Obligation obligation = new Obligation();
        obligation.setDescription(obligationForm.getDescription());
        obligation.setType(obligationForm.getType());
        obligation.setDueDate(obligationForm.getDueDate());
        obligation.setAmount(obligationForm.getAmount());
        obligation.setBusiness(selectedBusiness);

        obligationRepository.save(obligation);

        return String.format("redirect:/businesses/%d/obligation", selectedBusiness.getBusinessId());
    }

    @PostMapping("/delete")
    public String deleteObligation(@RequestParam Long obligationId,
                              @SessionAttribute(name = "selectedBusiness") Business business) {

        obligationRepository.deleteById(obligationId);

        return String.format("redirect:/businesses/%d/obligation", business.getBusinessId());
    }

}
