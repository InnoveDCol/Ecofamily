package com.innovedcol.ecofamily.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "ENTERPRISES")
public class Enterprise {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy="enterprise")
    @Column(name="employees", nullable = true)
    private List<Employee> employees;

    @OneToMany(mappedBy="enterprise")
    @Column(name="transactions", nullable = true)
    private List<Transaction> transactions;

    //@Temporal(TemporalType.DATE)
    @CreationTimestamp // TODO Cambio realizado por taller del 17/09/22 Verificar si funciona o si hay que poner LocalDateTime
    @Column(nullable = false)
    //private Calendar createdAt;
    private LocalDateTime createdAt;


    //@Temporal(TemporalType.DATE)
    @UpdateTimestamp // TODO Cambio realizado por taller del 17/09/22 Verificar si funciona o si hay que poner LocalDateTime
    @Column(nullable = true)
    //private Calendar updatedAt;
    private LocalDateTime updatedAt;

    // Constructor vacio
    public Enterprise() {
    }

    // Constructor completo
    public Enterprise(Long id, String name, String document, String phone, String address, List<Employee> employees, List<Transaction> transactions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.employees = employees;
        this.transactions = transactions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> listEmployees) {
        this.employees = listEmployees;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> listTransactions) {
        this.transactions = listTransactions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", users=" + employees +
                ", transactions=" + transactions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}