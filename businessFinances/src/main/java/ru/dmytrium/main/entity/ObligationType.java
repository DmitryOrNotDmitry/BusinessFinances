package ru.dmytrium.main.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "oblig_type")
@Data
public class ObligationType {

    @Id
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "oblig_type_name")
    private String obligTypeName;
}
