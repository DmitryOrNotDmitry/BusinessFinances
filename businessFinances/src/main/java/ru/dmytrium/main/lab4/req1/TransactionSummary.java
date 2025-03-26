package ru.dmytrium.main.lab4.req1;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionSummary {

    private Long totalTransactions;
    private BigDecimal totalAmount;
    private BigDecimal avgTransaction;
    private Date minDate;
    private Date maxDate;

    public TransactionSummary(Long totalTransactions, BigDecimal totalAmount, BigDecimal avgTransaction, Date minDate, Date maxDate) {
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
        this.avgTransaction = avgTransaction;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }
}

