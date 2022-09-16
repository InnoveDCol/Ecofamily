package com.innovedcol.ecofamily.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovedcol.ecofamily.enums.RoleEmployeeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private Enterprise enterprise;

    @OneToMany(mappedBy= "employee")
    private List<Transaction> transactions;

    @CreationTimestamp
    @Column (nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (nullable = true)
    private LocalDateTime updatedAt;

}
