package ru.dmytrium.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InvolveBusiness;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.repo.InvolveBusinessRepository;

import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private InvolveBusinessRepository involveRepository;

    public boolean isUserHasAnyRoleInBusiness(User user, Long businessId) {
        Optional<InvolveBusiness> involves = involveRepository.findByBusinessAndUser(new Business(businessId), user);
        return involves.isPresent();
    }

}
