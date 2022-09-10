package com.innovedcol.ecofamily.controllers;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeeController {

    // ATRIBUTOS
    // Objeto de tipo EmployeeService.
    private EmployeeService service;

    // CONSTRUCTOR
    public EmployeeController(EmployeeService employeeService) { this.service = employeeService; }

    // Método para llamar al servicio que retorna el arraylist de todos los empleados.
    @GetMapping("/users")
    public ArrayList<Employee> EmployeeList () {
        return this.service.getEmployeesList();
    }

    // Método para llamar al servicio que crea un nuevo empleado.
    @PostMapping("/users")
    public String createEmployee (@RequestBody Employee e) {
        return this.service.createEmployee(e);
    }

    // Método para llamar al servicio que busca un empleado de acuerdo a su index.
    // TODO: CAMBIAR EL TIPO DE RETORNO DEL METODO A OPTIONAL<EMPLOYEE>.
    @GetMapping("/user/{id}")
    public Employee searchEmployee(@PathVariable("id") int id){
        return this.service.searchEmployee(id);
    }

    // Método para llamar al servicio que actualiza la info de un employee.
    @PatchMapping("/user/{i}")
    public String updateEmployee(@PathVariable("i") int i, @RequestBody Employee emp) { return this.service.updateEmployee(i,emp); }

    // Método para llamar al servicio que eliminar un perfil
    @DeleteMapping("/user/{i}")
    public String deleteEmployee(@PathVariable("id") int i) { return this.service.deleteEmployee(i); }

}
