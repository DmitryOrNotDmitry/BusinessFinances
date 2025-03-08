package ru.dmytrium.main.entity.form;

import lombok.Data;

@Data
public class UserTraitsForm {

    private String newName;
    private String newEmail;
    private String oldPassword;
    private String newPassword;
    private String repeatedNewPassword;

    public UserTraitsForm(String newName, String newEmail) {
        this.newName = newName;
        this.newEmail = newEmail;
    }
}
