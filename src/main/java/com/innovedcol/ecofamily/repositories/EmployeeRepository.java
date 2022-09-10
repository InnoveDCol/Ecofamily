package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
