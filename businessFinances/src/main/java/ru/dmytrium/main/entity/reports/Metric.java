package ru.dmytrium.main.entity.reports;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Metric {

    private String name;
    private BigDecimal value;

}
