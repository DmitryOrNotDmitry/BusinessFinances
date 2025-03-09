package ru.dmytrium.main.entity.reports;

import lombok.Data;

import java.util.Date;

@Data
public class MoneyFlowForm {

    private Date startDate;
    private Date endDate;

}
