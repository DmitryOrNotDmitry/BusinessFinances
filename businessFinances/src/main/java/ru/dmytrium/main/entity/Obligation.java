package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "obligation")
@Data
public class Obligation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obligation_id")
    private Long obligationId;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "type_name")
    private InOutType type;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;
}
