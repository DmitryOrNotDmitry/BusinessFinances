package ru.dmytrium.main.entity.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.dmytrium.main.entity.ObligationType;
import ru.dmytrium.main.entity.Transaction;

import java.util.Date;
import java.util.List;

@Data
public class ObligationForm {

    private ObligationType type;
    private String description;
    private List<Transaction> transactions;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dueDate;

}
