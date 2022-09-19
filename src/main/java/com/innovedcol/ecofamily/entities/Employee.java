package com.innovedcol.ecofamily.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovedcol.ecofamily.enums.RoleEmployeeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@JsonIgnoreProperties(value= {"enterprise"})
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column (nullable = false, length = 50)
    private String name;

    @Column (nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column (name="role", nullable = false)
    private RoleEmployeeEnum role;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    //@JsonIgnore
    private Enterprise enterprise;

    @OneToMany(mappedBy= "employee")
    private List<Transaction> transactions;

    @CreationTimestamp
    @Column (nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (nullable = true)
    private LocalDateTime updatedAt;
    //MÉTODOS

    //Constructores:

    public Employee() {
    }

    public Employee(Long id, String name, String email, String phone, RoleEmployeeEnum role, String image, Enterprise enterprise, List<Transaction> transactions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.image = image;
        this.enterprise = enterprise;
        this.transactions = transactions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleEmployeeEnum getRole() {
        return role;
    }

    public void setRole(RoleEmployeeEnum rol) {
        this.role = rol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterpriseContratante) {
        this.enterprise = enterpriseContratante;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactionList) {
        this.transactions = transactionList;
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rol=" + role +
                ", image='" + image + '\'' +
                ", enterpriseContratante=" + enterprise +
                ", transactionList=" + transactions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
