package com.innovedcol.ecofamily.entities;

import java.util.ArrayList;

public class Employee {

    private long id;
    private String name;
    private String email;
    private Profile profile;
    //private Enum_RoleName;
    private Enterprise enterpriseContratante;
    private String rol;

    // TODO: Atributo para listar los movimientos de dinero de una empresa
    private ArrayList<Transaction> transactionList;

    public Employee(String name, String email, Enterprise enterpriseContratante, String rol) {
        this.name = name;
        this.email = email;
        this.enterpriseContratante = enterpriseContratante;
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {this.name = name;}

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name = '" + name + '\'' +
                ", email ='" +  + '\'' +
                ", enterpriseContratante='" + enterpriseContratante.getName() + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
