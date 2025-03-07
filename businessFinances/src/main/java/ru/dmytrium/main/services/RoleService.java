package ru.dmytrium.main.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.Role;
import ru.dmytrium.main.repo.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getOwnerRole() {
        return roleRepository.findByRoleName("Владелец")
                .orElseThrow(() -> new EntityNotFoundException("Роль 'Владелец' не найдена"));
    }
}

