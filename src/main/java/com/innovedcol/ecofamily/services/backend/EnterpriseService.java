package com.innovedcol.ecofamily.services.backend;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnterpriseService {

    // Definimos un atributo de tipo repositorio
    private final EnterpriseRepository repository;

    // Método que retorna un arraylist con el listado de las empresas:
    public List<?> getEnterprisesList(){
        List<Enterprise> list = repository.findAll();
        if (list.size() > 0){
            return list;
        }else{
            return new ArrayList<String>() {{
                add("No existen empresas");
            }};
        }
    }

    // Método que retorna un objeto de tipo Enterprise según su ID:
    public Optional<Enterprise> searchEnterprise(Long id){
        return repository.findById(id);
    }

    public Optional<Enterprise> searchEnterpriseName(String name){
        return repository.findByName(name);
    }

    public Optional<Enterprise> searchEnterpriseDocument(String document){
        return repository.findByDocument(document);
    }

    // Método que retorna un objeto de tipo Transaction que hacen parte de una empresa:
    public List<?> searchTransactionsEnterprise(Long id){
        if(searchEnterprise(id).isPresent()){
            List<Transaction> list = repository.findById(id).get().getTransactions();
            if (list.size() > 0){
                return list;
            }else{
                return new ArrayList<String>() {{
                    add("Empresa sin transacciones");
                }};
            }
        }else{
            return new ArrayList<String>() {{
                add("La empresa no existe!");
            }};
        }
    }

    // Método que retorna un objeto de tipo Employee que hacen parte de una empresa:
    public List<?> searchEmployeesEnterprise(Long id){
        if(searchEnterprise(id).isPresent()){
            List<Employee> list = repository.findById(id).get().getEmployees();
            if (list.size() > 0){
                return list;
            }else{
                return new ArrayList<String>() {{
                    add("Empresa sin empleados");
                }};
            }
        }else{
            return new ArrayList<String>() {{
                add("La empresa no existe!");
            }};
        }
    }

    // Método que crea una empresa y la añade a la base de datos. Retorna un mensaje
    public String createEnterprise(Enterprise e){
        if(searchEnterprise(e.getId()).isEmpty()){
            if(searchEnterpriseName(e.getName()).isEmpty()) {
                if(searchEnterpriseDocument(e.getDocument()).isEmpty()) {
                    repository.save(e);
                    return "--> Empresa creada con éxito!";
                }else{
                    return "--> El documento ingresado ya esta asociado a otra empresa!";
                }
            }else{
                return "--> El nombre ingresado ya esta asociado a otra empresa!";
            }
        }else{
            return "--> Empresa ya existe!";
        }
    }
    // Método que actualiza la información de una empresa según su id. Retorna un mensaje
    public String updateEnterprise(Long id, Enterprise e){
        List<Employee> empleadosActuales;
        List<Transaction> transaccionesActuales;
        LocalDateTime fechaCreacionActual;

        if(searchEnterprise(id).isPresent()){
            empleadosActuales = searchEnterprise(id).get().getEmployees();
            transaccionesActuales = searchEnterprise(id).get().getTransactions();
            fechaCreacionActual = searchEnterprise(id).get().getCreatedAt();
            e.setEmployees(empleadosActuales);
            e.setCreatedAt(fechaCreacionActual);
            e.setTransactions(transaccionesActuales);
            repository.save(e);
            return "--> Empresa actualizada con éxito!";
        }else{
            return "--> La empresa indicada no existe!";
        }
    }

    // Método que elimina una empresa de la base de datos. Retorna un mensaje
    public String deleteEnterprise(Long id){
        if(searchEnterprise(id).isPresent()){
            repository.deleteById(id);
            return "--> Empresa eliminada con éxito!";
        }else{
            return "--> La empresa indicada no existe!";
        }
    }
}