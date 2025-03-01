package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "considering")
@Data
public class Considering {

    @Id
    @ManyToOne
    @JoinColumn(name = "transaction_code")
    private Transaction transaction;

    @Id
    @ManyToOne
    @JoinColumn(name = "obligation_id")
    private Obligation obligation;
}