package com.innovedcol.ecofamily.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Profile")
public class Profile {

    // Atributos
    @Id
    @Column(unique = true)
    private String id;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String phone;
    //TODO: Quitar comentarios de las 2 lineas siguientes cuando se complete la Entidad Employee
    //@OneToOne
    //@JoinColumn(nullable = true)
    private Employee user;
    @Column(nullable = false)
    private Calendar createdAt;
    @Column(nullable = false)
    private Calendar updatedAt;

    // Constructor vacío
    public Profile() {
    }

    // Constructor completo
    public Profile(String id, String image, String phone, Employee user, Calendar createdAt, Calendar updatedAt) {
        this.id = id;
        this.image = image;
        this.phone = phone;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
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
        return "Profile{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
