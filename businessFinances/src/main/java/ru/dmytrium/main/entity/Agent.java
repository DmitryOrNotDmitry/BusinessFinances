package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "agent")
@Data
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "for_business_id")
    private Business forBusiness;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "created_at")
    private Date createdAt;
}
