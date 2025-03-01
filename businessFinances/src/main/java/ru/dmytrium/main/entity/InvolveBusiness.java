package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "involve_business")
@Data
public class InvolveBusiness {

    @Id
    @Column(name = "involve_code")
    private Long involveCode;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "joined_at")
    private Date joinedAt;
}
