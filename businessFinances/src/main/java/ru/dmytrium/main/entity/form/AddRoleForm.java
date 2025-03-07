package ru.dmytrium.main.entity.form;

import lombok.Data;
import ru.dmytrium.main.entity.Role;

@Data
public class AddRoleForm {

    private String login;
    private Role role;

}
