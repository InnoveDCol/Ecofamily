package com.innovedcol.ecofamily.services.frontend;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.repositories.EmployeeRepository;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import com.innovedcol.ecofamily.repositories.TransactionRepository;
import com.innovedcol.ecofamily.services.backend.EmployeeService;
import com.innovedcol.ecofamily.services.backend.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TranFEService {

    // Definimos un atributo de tipo repositorio
    private final TransactionRepository transactionRepository;
    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EmployeeService employeeService;
    private final EnterpriseService enterpriseService;

    // Método que retorna un arraylist con el listado de las transacciones
    public List<?> getTransactionsList(){
        List<Transaction> list = transactionRepository.findAll();
        if (list.size() > 0){
            return list;
        }else{
            return new ArrayList<String>() {{
                add("No existen transacciones");
            }};
        }
    }

    // Método que retorna un objeto de tipo Transaction según su ID
    public Optional<Transaction> searchTransaction(Long id){
        return transactionRepository.findById(id);
    }

    // Método que crea una transacción y la añade a la base de datos. Retorna un mensaje
    public String createTransaction(Long user_id, Long enterprise_id, Transaction t){
        if(enterpriseService.searchEnterprise(enterprise_id).isPresent()){
            if(employeeService.searchEmployee(user_id).isPresent()) {
                List<Employee> list = enterpriseRepository.findById(enterprise_id).get().getEmployees();
                for (Employee emp : list) {
                    if (Objects.equals(emp.getId(), user_id)) {
                        try{
                            employeeRepository.findById(user_id).map(usr -> {
                                t.setEmployee(usr);
                                return usr;
                            });
                            enterpriseRepository.findById(enterprise_id).map(ent -> {
                                t.setEnterprise(ent);
                                return transactionRepository.save(t);
                            });
                            return "--> Transacción creada con éxito!";

                        }catch(Exception e){
                            return "--> La transacción no fue creada!";
                        }
                    }
                }
            }else {
                return "El empleado ingresado no existe!";
            }
        }else {
            return "La empresa ingresada no existe!";
        }

    return "--> El empleado ingresado no hace parte de la empresa indicada!";
    }

    // Método que actualiza la información de una transacción según su id. Retorna un mensaje
    public String updateTransaction(Long id, Transaction t){
        Enterprise empresaActual;
        Employee empleadoActual;
        LocalDateTime fechaCreacionActual;

        if(searchTransaction(id).isPresent()){
            empresaActual = searchTransaction(id).get().getEnterprise();
            empleadoActual = searchTransaction(id).get().getEmployee();
            fechaCreacionActual = searchTransaction(id).get().getCreatedAt();

            t.setEnterprise(empresaActual);
            t.setEmployee(empleadoActual);
            t.setCreatedAt(fechaCreacionActual);

            transactionRepository.save(t);
            return "--> Transacción actualizada con éxito!";
        }else{
            return "--> La transacción indicada no existe!";
        }
    }

    // Método que elimina una transacción de la base de datos. Retorna un mensaje
    public String deleteTransaction(Long id){
        if(searchTransaction(id).isPresent()){
            transactionRepository.deleteById(id);
            return "--> Transacción eliminada con éxito!";
        }else{
            return "--> La transacción indicada no existe!";
        }
    }
}