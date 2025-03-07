package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "business")
@Data
public class Business {

    public Business() {
    }

    public Business(Long businessId) {
        this.businessId = businessId;
    }

    public Business clone() {
        Business clone = new Business();
        clone.businessId = this.businessId;
        clone.businessName = this.businessName;
        clone.author = this.author;
        clone.createdAt = this.createdAt;
        return clone;
    }

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
