package ru.dmytrium.main.lab4.req3;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyTransactionReport {
    private int year;
    private int month;
    private long totalTransactions;
    private BigDecimal avgAmount;
    private BigDecimal maxTransaction;

    public MonthlyTransactionReport(int year, int month, long totalTransactions, BigDecimal avgAmount, BigDecimal maxTransaction) {
        this.year = year;
        this.month = month;
        this.totalTransactions = totalTransactions;
        this.avgAmount = avgAmount;
        this.maxTransaction = maxTransaction;
    }
}

