package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @Column(name = "account_code")
    private Long accountCode;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;
}
