package com.innovedcol.ecofamily.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innovedcol.ecofamily.enums.EnumTypeTransaction;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String concept;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "employee")//Corregido.
    @JsonIgnore
    private Employee user;

    @ManyToOne
    @JoinColumn(name = "enterprise")//Corregido.
    @JsonIgnore
    private Enterprise enterprise;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumTypeTransaction type;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date updatedAt;

    // Constructor vacio
    public Transaction() {
    }

    // Constructor completo
    public Transaction(Long id, String concept, double amount, Employee user, Enterprise enterprise, EnumTypeTransaction type, Date createdAt, Date updatedAt) {
        this.id = id;
        this.concept = concept;
        this.amount = amount;
        this.user = user;
        this.enterprise = enterprise;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters:

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public EnumTypeTransaction getType() {
        return type;
    }

    public void setType(EnumTypeTransaction type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", enterprise=" + enterprise +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
