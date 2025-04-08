package ru.dmytrium.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "in_out_type")
@Data
public class InOutType {

    @Id
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;
}
