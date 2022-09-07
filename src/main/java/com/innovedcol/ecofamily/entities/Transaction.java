package com.innovedcol.ecofamily.entities;

import javax.persistence.*;

@Entity
@Table(name = "Transaction")
public class Transaction {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;
    @Column(nullable = false)
    private String concept;
    @Column(nullable = false)
    private double amount;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Enterprise enterprise;
    @Column(nullable = false)
    private String type;

    // Constructor vacio
    public Transaction() {
    }

    // Constructor completo
    public Transaction(Long id, String concept, double amount, Employee user, Enterprise enterprise, String type) {
        this.id = id;
        this.concept = concept;
        this.amount = amount;
        this.user = user;
        this.enterprise = enterprise;
        this.type = type;
    }

    // Getters and setters

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", enterprise=" + enterprise +
                ", type='" + type + '\'' +
                '}';
    }
}
