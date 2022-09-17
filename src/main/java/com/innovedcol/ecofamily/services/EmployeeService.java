package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.repositories.EmployeeRepository;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseService enterpriseService;

    //MÉTODOS//

    //Constructor:
    public EmployeeService(EmployeeRepository employeeRepository, EnterpriseRepository enterpriseRepository, EnterpriseService enterpriseService) {
        this.employeeRepository = employeeRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseService = enterpriseService;
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

    public Employee createEmployee(Long enterprise_id, Employee e) {
        try {
            if(searchEmployee(e.getId()).isEmpty()) {
                if(enterpriseService.searchEnterprise(enterprise_id).isPresent()){
                    return enterpriseRepository.findById(enterprise_id).map(ent -> {
                        e.setEnterpriseContratante(ent);
                        return employeeRepository.save(e);
                    }).get();
                }else {
                    return new Employee();
                }
            }else {
                return new Employee();
            }
        } catch (Exception ex) {
            return null;
        }
    }


    // Actualizar: Método que actualiza la información de un empleado según su id. Retorna un mensaje:
    //TODO -> Corregir que al modificar borra la info de la empresa, ya que esta no aparece en el json
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
