package com.innovedcol.ecofamily.entities;

public class Employee {

    private String nombre;
    private String correo;
    private Enterprise enterpriseContratante;
    private String rol;

    public Employee(String nombre, String correo, Enterprise enterpriseContratante, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.enterpriseContratante = enterpriseContratante;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Enterprise getEmpresaContratante() {
        return enterpriseContratante;
    }

    public void setEmpresaContratante(Enterprise enterpriseContratante) {
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
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", enterpriseContratante='" + enterpriseContratante.getNombre() + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
