package ru.dmytrium.main.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dmytrium.main.entity.Role;
import ru.dmytrium.main.repo.RoleRepository;

@Component
public class StringToRoleConverter implements Converter<String, Role> {
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role convert(String source) {
        return roleRepository.findByRoleName(source).orElseThrow(() ->
                new IllegalArgumentException("Роль не найдена: " + source));
    }
}
