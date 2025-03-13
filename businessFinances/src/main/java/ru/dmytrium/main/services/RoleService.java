package ru.dmytrium.main.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Role;
import ru.dmytrium.main.repo.RoleRepository;

@Service
public class RoleService {

    public final static String ROLE_OWNER = "Владелец";
    public final static String ROLE_ADMIN = "Администратор";
    public final static String ROLE_WORKER = "Работник";

    @Autowired
    private RoleRepository roleRepository;

    public Role getOwnerRole() {
        return roleRepository.findByRoleName(ROLE_OWNER)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Роль '%s' не найдена", ROLE_OWNER)));
    }
}

