package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    EnterpriseService Enterprise;//Variable(Enterprise) de tipo EnterpriseService.
    ArrayList<Employee> EmployeesList= new ArrayList<>();//Creamos el arraylist que contendrá los empleados.

    //MÉTODOS//

    //Constructor(vacío): Tendrá los datos iniciales de empleados.
    public EmployeeService() {
        this.Enterprise=new EnterpriseService();//Creamos el objeto para inicializar el servicio empresas.
        this.initialData();
    }

    // Datos iniciales:
    public void initialData(){
        EmployeesList.add(new Employee("Matthew Zuñiga","juan@jzgdevelopers.com",Enterprise.searchEnterprise(2),"Admin"));
        EmployeesList.add(new Employee("Alexander Carvajal","alexander@automantec.com",Enterprise.searchEnterprise(1),"Operario"));
        EmployeesList.add(new Employee("Alejandra Moreano", "alejandra@ecofamily.com", Enterprise.searchEnterprise(0),"Operario"));
    }

    // Método que retorna el arraylist de todos los empleados:
    public ArrayList<Employee> getEmployeesList(){
        return EmployeesList;
    }

    // Método que retorna la información de un empleado del listado según su index:
    public Employee searchEmployee(int index){
        return EmployeesList.get(index);
    }

    // Método que crea un empleado y la añade al listado, retornando un mensaje:
    public String createEmployee(Employee emp){
        EmployeesList.add(emp);
        return "--> Empleado creado con éxito";
    }

    // Método que actualiza la información de una empresa según su index, retornando un mensaje:
    public String updateEmployee(int index, Employee emp){
        EmployeesList.set(index, emp);
        return "--> Empleado actualizado con éxito";
    }

    // Método que elimina un empleado del listado, retornando un mensaje:
    public String deleteEmployee(int index){
        EmployeesList.remove(index);
        return "--> Empleado eliminado";
    }

}
