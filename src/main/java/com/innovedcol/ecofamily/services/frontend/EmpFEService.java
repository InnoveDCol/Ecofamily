package com.innovedcol.ecofamily.services.frontend;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.enums.EnumRoleEmployee;
import com.innovedcol.ecofamily.repositories.EmployeeRepository;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import com.innovedcol.ecofamily.services.backend.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpFEService {

    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseService enterpriseService;

    // Método que retorna el arraylist de todos los empleados:
    public List<?> getEmployeesList() {
        List<Employee> list = employeeRepository.findAll();
        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<String>() {{
                add("No existen empleados");
            }};
        }
    }

    // Buscar: Método que retorna un objeto de tipo Employee según su ID:
    public Optional<Employee> searchEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> searchEmployeeEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Optional<Employee> searchRoleEmail(EnumRoleEmployee role) {
        return employeeRepository.findByRole(role);
    }

    // Método que retorna un objeto de tipo Transaction que hacen parte de una empresa:
    public List<?> searchTransactionsEmployee(Long id) {
        List<Transaction> list = employeeRepository.findById(id).get().getTransactions();
        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<String>() {{
                add("Empleado no existe");
            }};
        }
    }

    public String createEmployee(Long enterprise_id, Employee e) {

        if (searchEmployee(e.getId()).isEmpty()) {
            if (searchEmployeeEmail(e.getEmail()).isEmpty()) {
                if (enterpriseService.searchEnterprise(enterprise_id).isPresent()) {
                    enterpriseRepository.findById(enterprise_id).map(ent -> {
                        e.setEnterprise(ent);
                        return employeeRepository.save(e);
                    });
                    return "--> Empleado creado con éxito!";
                } else {
                    return "--> La empresa ingresada no existe!!";
                }
            } else {
                return "--> El email ingresado ya esta asociado a otro empleado!";
            }
        } else {
            return "--> El empleado ya existe!";
        }
    }

    // Actualizar: Método que actualiza la información de un empleado según su id. Retorna un mensaje:
    public String updateEmployee(Long id, Employee emp) {
        LocalDateTime fechaCreacionActual;
        List<Transaction> transaccionesActuales;
        String emailActual;
        EnumRoleEmployee rolActual;

        if (searchEmployee(id).isPresent()) {
            emailActual = searchEmployee(id).get().getEmail();
            fechaCreacionActual = searchEmployee(id).get().getCreatedAt();
            transaccionesActuales = searchEmployee(id).get().getTransactions();
            rolActual = searchEmployee(id).get().getRole();

            if (emp.getRole() == null) {
                emp.setRole(rolActual);
            }
            if (emp.getEnterprise() == null) {
                emp.setEnterprise((Enterprise) enterpriseService.getEnterprisesList().get(0));
            }

            emp.setEmail(emailActual);
            emp.setCreatedAt(fechaCreacionActual);
            emp.setTransactions(transaccionesActuales);
            employeeRepository.save(emp);
            return "--> Empleado actualizado con éxito!";
        } else {
            return "--> El empleado indicado no existe!";
        }
    }

    // Eliminar: Método que elimina un empleado de la base de datos. Retorna un mensaje:
    public String deleteEmployee(Long id) {
        if (searchEmployee(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "--> Empleado eliminado con éxito!";
        } else {
            return "--> El empleado indicado no existe!";
        }
    }

    public Employee createOrValidateUser(Map<String, Object> dataUser) {
        Optional<Employee> usuario = searchEmployeeEmail((String) dataUser.get("email"));
        if (usuario.isEmpty()) {
            String name = (String) dataUser.get("name");
            String email = (String) dataUser.get("email");
            String phone = (String) dataUser.get("phone");
            String image = (String) dataUser.get("picture");
            if (image.equals("null")) {
                image = "https://i.ibb.co/4jrvy5h/ecofamily.png";
            }
            LocalDateTime fechaCreacion = LocalDateTime.now();
            LocalDateTime fechaActualizacion = LocalDateTime.now();
            EnumRoleEmployee rol;
            if (searchRoleEmail(EnumRoleEmployee.valueOf("Admin")).isPresent()) {
                rol = EnumRoleEmployee.valueOf("Operario");
            } else {
                rol = EnumRoleEmployee.valueOf("Admin");
            }

            Enterprise enterprise = extractEnterpriseInfo();

            Employee employee = new Employee(0L, name, email, phone, rol, image, enterprise, null, fechaCreacion, fechaActualizacion);
            employeeRepository.save(employee);
            return employee;
        } else {
            return usuario.get();
        }
    }

    public Enterprise extractEnterpriseInfo() {
        List<Enterprise> list = enterpriseRepository.findAll();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}