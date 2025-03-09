package ru.dmytrium.main.entity.reports;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyFlowSummary {

    private String name;
    private BigDecimal inTotal;
    private BigDecimal outTotal;

}
