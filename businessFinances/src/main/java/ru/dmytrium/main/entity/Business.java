package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "business")
@Data
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Long businessId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "created_at")
    private Date createdAt;
}
