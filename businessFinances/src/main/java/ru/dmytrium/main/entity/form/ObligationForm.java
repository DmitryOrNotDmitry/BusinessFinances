package ru.dmytrium.main.entity.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.dmytrium.main.entity.InOutType;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ObligationForm {

    private InOutType type;
    private String description;
    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dueDate;

}
