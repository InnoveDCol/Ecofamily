package com.innovedcol.ecofamily.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ENTERPRISES")
public class Enterprise {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "enterpriseContratante")//Corregido.
    @Column(name="employees")
    private List<Employee> users;

    @OneToMany(mappedBy = "enterprise")//Corregido.
    @Column(nullable = true)
    private List<Transaction> transactions;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar createdAt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar updatedAt;

    // Constructor vacio
    public Enterprise() {
    }

    // Constructor completo
    public Enterprise(Long id, String name, String document, String phone, String address, List<Employee> users, List<Transaction> transactions, Calendar createdAt, Calendar updatedAt) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.users = users;
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

    public List<Employee> getUsers() {
        return users;
    }

    public void setUsers(List<Employee> listEmployees) {
        this.users = listEmployees;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> listTransactions) {
        this.transactions = listTransactions;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
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
                ", users=" + users +
                ", transactions=" + transactions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}