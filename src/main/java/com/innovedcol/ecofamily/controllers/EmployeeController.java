package com.innovedcol.ecofamily.controllers;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.services.EmployeeService;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@RestController
public class EmployeeController {

    // ATRIBUTOS
    // Objeto de tipo EmployeeService.
    private EmployeeService service;

    // CONSTRUCTOR
    public EmployeeController(EmployeeService employeeService) { this.service = employeeService; }

    // Método para llamar al servicio que retorna el arraylist de todos los empleados:
    @GetMapping("/users")
    public List<?> EmployeeList () {
        return this.service.getEmployeesList();
    }

    /*
    // Método para llamar al servicio que crea un nuevo empleado:
    @PostMapping("/users")
    public String createEmployee (@RequestBody Employee e) {
        return this.service.createEmployee(e);
    }
    */

    // Método para llamar al servicio que crea un nuevo empleado en una empresa establecida
    @PostMapping("/users/{ent_id}")
    public String createEmployee(@PathVariable("ent_id") Long ent_id,@RequestBody Employee e) {
        return this.service.createEmployee(ent_id, e);
    }

    // Método para llamar al servicio que busca un empleado de acuerdo a su id:
    @GetMapping("/user/{id}")
    public Optional<Employee> searchEmployee(@PathVariable("id") Long id){
        return this.service.searchEmployee(id);
    }

    // Método para llamar al servicio que busca las transacciones de una empresa de acuerdo a su id:
    @GetMapping("/user/{id}/movements")
    public List<Object> searchTransactionsEmployee(@PathVariable("id") Long id){
        return this.service.searchTransactionsEmployee(id);
    }

    // Método para llamar al servicio que actualiza la info de un employee:
    @PatchMapping("/user/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @RequestBody Employee emp) { return this.service.updateEmployee(id,emp); }

    // Método para llamar al servicio que elimina un perfil:
    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) { return this.service.deleteEmployee(id); }

}
