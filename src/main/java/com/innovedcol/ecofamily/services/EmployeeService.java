package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //MÉTODOS//

    //Constructor:
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Método que retorna el arraylist de todos los empleados:
    public ArrayList<Employee> getEmployeesList(){
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    // Buscar: Método que retorna un objeto de tipo Employee según su ID:
    public Optional<Employee> searchEmployee(Long id){
        return employeeRepository.findById(id);
    }

    // Insertar: Método que crea un empleado y la añade a la base de datos. Retorna un mensaje:
    public String createEmployee(Employee emp){
        if(searchEmployee(emp.getId()).isEmpty()){
            employeeRepository.save(emp);
            return "--> Empleado creado con éxito!";
        }else{
            return "--> El empleado ya existe!";
        }
    }

    // Actualizar: Método que actualiza la información de un empleado según su id. Retorna un mensaje:
    public String updateEmployee(Long id, Employee emp){
        if(searchEmployee(id).isPresent()){
            employeeRepository.save(emp);
            return "--> Empleado actualizado con éxito!";
        }else{
            return "--> El empleado indicado no existe!";
        }
    }

    // Eliminar: Método que elimina un empleado de la base de datos. Retorna un mensaje:
    public String deleteEmployee(Long id){
        if(searchEmployee(id).isPresent()){
            employeeRepository.deleteById(id);
            return "--> Empleado eliminado con éxito!";
        }else{
            return "--> El empleado indicado no existe!";
        }
    }

}
