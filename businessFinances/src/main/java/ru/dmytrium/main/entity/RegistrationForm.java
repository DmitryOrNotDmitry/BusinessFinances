package ru.dmytrium.main.entity;

import lombok.Data;

@Data
public class RegistrationForm {

    private String login;
    private String password;
    private String repeatedPassword;
    private String email;

}
