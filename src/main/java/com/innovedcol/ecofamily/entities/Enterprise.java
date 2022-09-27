package com.innovedcol.ecofamily.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
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

    @OneToMany(mappedBy="enterprise", cascade = CascadeType.ALL)
    @Column(name="employees", nullable = true)
    private List<Employee> employees;

    @OneToMany(mappedBy="enterprise", cascade = CascadeType.ALL)
    @Column(name="transactions", nullable = true)
    private List<Transaction> transactions;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updatedAt;

}