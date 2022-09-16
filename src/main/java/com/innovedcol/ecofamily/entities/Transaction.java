package com.innovedcol.ecofamily.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innovedcol.ecofamily.enums.EnumTypeTransaction;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "TRANSACTIONS")
public class Transaction {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(nullable = false)
    private String concept;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "employee")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    @JsonIgnore
    private Enterprise enterprise;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumTypeTransaction type;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updatedAt;

}