package com.innovedcol.ecofamily.services.frontend;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.repositories.EmployeeRepository;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import com.innovedcol.ecofamily.services.backend.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpFEService {

    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseService enterpriseService;

    // Método que retorna el arraylist de todos los empleados:
    public List<?> getEmployeesList(){
        List<Employee> list = employeeRepository.findAll();
        if (list.size() > 0){
            return list;
        }else{
            return new ArrayList<String>() {{
                add("No existen empleados");
            }};
        }
    }

    // Buscar: Método que retorna un objeto de tipo Employee según su ID:
    public Optional<Employee> searchEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public Optional<Employee> searchEmployeeEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    // Método que retorna un objeto de tipo Transaction que hacen parte de una empresa:
    public List<?> searchTransactionsEmployee(Long id){
    List<Transaction> list = employeeRepository.findById(id).get().getTransactions();
        if (list.size() > 0){
            return list;
        }else{
            return new ArrayList<String>() {{
                add("Empleado no existe");
            }};
        }
    }

    public String createEmployee(Long enterprise_id, Employee e) {

        if(searchEmployee(e.getId()).isEmpty()) {
            if(searchEmployeeEmail(e.getEmail()).isEmpty()) {
                if(enterpriseService.searchEnterprise(enterprise_id).isPresent()){
                    enterpriseRepository.findById(enterprise_id).map(ent -> {
                        e.setEnterprise(ent);
                        return employeeRepository.save(e);
                    });
                    return "--> Empleado creado con éxito!";
                }else {
                    return "--> La empresa ingresada no existe!!";
                }
            }else{
                return "--> El email ingresado ya esta asociado a otro empleado!";
            }
        }else {
            return "--> El empleado ya existe!";
        }
    }

    // Actualizar: Método que actualiza la información de un empleado según su id. Retorna un mensaje:
    public String updateEmployee(Long id, Employee emp){
        Enterprise empresaActual;
        LocalDateTime fechaCreacionActual;
        List<Transaction> transaccionesActuales;

        if(searchEmployee(id).isPresent()){
            empresaActual = searchEmployee(id).get().getEnterprise();
            fechaCreacionActual = searchEmployee(id).get().getCreatedAt();
            transaccionesActuales = searchEmployee(id).get().getTransactions();
            emp.setEnterprise(empresaActual);
            emp.setCreatedAt(fechaCreacionActual);
            emp.setTransactions(transaccionesActuales);
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