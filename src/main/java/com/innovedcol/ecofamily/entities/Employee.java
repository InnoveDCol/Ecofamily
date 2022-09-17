package com.innovedcol.ecofamily.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innovedcol.ecofamily.enums.RoleEmployeeEnum;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    //@Column (unique = true)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 50)
    private String name;

    @Column (nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column (name="role", nullable = false)
    private RoleEmployeeEnum rol;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    @JsonIgnore
    private Enterprise enterpriseContratante;

    @OneToMany(mappedBy = "user")//Corregido.
    private List<Transaction> transactionList;

    @Temporal(TemporalType.DATE)
    @Column (nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column (nullable = true)
    private Date updatedAt;
    //MÉTODOS

    //Constructores:

    public Employee() {
    }

    public Employee(Long id, String name, String email, String phone, RoleEmployeeEnum rol, String image, Enterprise enterpriseContratante, List<Transaction> transactionList, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rol = rol;
        this.image = image;
        this.enterpriseContratante = enterpriseContratante;
        this.transactionList = transactionList;
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

    public RoleEmployeeEnum getRol() {
        return rol;
    }

    public void setRol(RoleEmployeeEnum rol) {
        this.rol = rol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enterprise getEnterpriseContratante() {
        return enterpriseContratante;
    }

    public void setEnterpriseContratante(Enterprise enterpriseContratante) {
        this.enterpriseContratante = enterpriseContratante;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
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
}
