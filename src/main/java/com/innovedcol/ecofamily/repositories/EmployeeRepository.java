package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.enums.EnumRoleEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByRole(EnumRoleEmployee rol);
}
