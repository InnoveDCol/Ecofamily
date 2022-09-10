package com.innovedcol.ecofamily.entities;

import com.innovedcol.ecofamily.enums.RoleEmployeeEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column (unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column (nullable = false, length = 50)
    private String name;
    @Column (nullable = false, length = 50)
    private String email;

    @OneToOne (cascade= {CascadeType.ALL})
    @JoinColumn (nullable = false)
    private Profile profile;

    @OneToMany(cascade= {CascadeType.ALL})
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private RoleEmployeeEnum rol;
    @Column (nullable = false)
    private Enterprise enterpriseContratante;

    // TODO: Atributo para listar los movimientos de dinero de una empresa.
    @Column (nullable = true, length = 50)
    private ArrayList<Transaction> transactionList;

    @Column (nullable = false)
    private Date updatedAt;

    @Column (nullable = false)
    private Date createdAt;

    public Employee() {
    }

    public Employee(String name, String email, Enterprise enterpriseContratante, RoleEmployeeEnum rol) {
        this.name = name;
        this.email = email;
        this.enterpriseContratante = enterpriseContratante;
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Enterprise getEnterpriseContratante() {
        return enterpriseContratante;
    }

    public void setEnterpriseContratante(Enterprise enterpriseContratante) {
        this.enterpriseContratante = enterpriseContratante;
    }

    public RoleEmployeeEnum getRol() {
        return rol;
    }

    public void setRol(RoleEmployeeEnum rol) {
        this.rol = rol;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }
    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override

    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profile=" + profile +
                ", rol=" + rol +
                ", enterpriseContratante=" + enterpriseContratante +
                ", transactionList=" + transactionList +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
