package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "type_name")
    private ObligationType type;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;
}
