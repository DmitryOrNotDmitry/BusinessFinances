package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_code")
    private Long transactionCode;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "account_code")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TransactionCategory category;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;
}
