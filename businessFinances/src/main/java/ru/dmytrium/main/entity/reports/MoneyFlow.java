package ru.dmytrium.main.entity.reports;

import lombok.Data;
import ru.dmytrium.main.entity.TransactionCategory;

import java.math.BigDecimal;

@Data
public class MoneyFlow {

    private TransactionCategory category;
    private BigDecimal amountSum;

}
