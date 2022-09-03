package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;

import java.util.ArrayList;

public class TransactionService {

    EmployeeService employeeService;

    // Creamos el arraylist que contendrá las transacciones
    ArrayList<Transaction> transactionsList = new ArrayList<>();

    public TransactionService() { // Constructor vacio
        this.employeeService = new EmployeeService(); // Inicializamos el servicio de empleados
        this.datosIniciales(); // Se llenan los datos iniciales de las transacciones
    }

    // Metodo para cargar datos iniciales de las transacciones
    public void datosIniciales(){
        //TODO: Implementar un metodo del servicio Employee para obtener los datos de un empleado segun si index y cambiar las lineas 24 a 100 por las lineas 102 a 108 que estan comentadas
        /*
        transactionsList.add(
                new Transaction(20000,
                        "Consignacion inicial",
                        "consignacion",
                        new Employee(
                                    "Catalina Taborda",
                                     "catalina@ecofamily.com",
                                new Enterprise("Ecofamily","Medellin","4561235","123456987-4"),
                                        "Admin")
                )
        );
        transactionsList.add(
                new Transaction(-10000,
                        "Retiro 30/08/22",
                        "retiro",
                        new Employee(
                                "Catalina Taborda",
                                "catalina@ecofamily.com",
                                new Enterprise("Ecofamily","Medellin","4561235","123456987-4"),
                                "Admin")
                )
        );
        transactionsList.add(
                new Transaction(1000000,
                        "Consignacion inicial",
                        "consignacion",
                        new Employee(
                                "Alejandra Moreano",
                                "alejandra@ecofamily.com",
                                new Enterprise("Ecofamily","Medellin","4561235","123456987-4"),
                                "Operario")
                )
        );
        transactionsList.add(
                new Transaction(-65000,
                        "Retiro 01/09/22",
                        "retiro",
                        new Employee(
                                "Carlos Mora",
                                "carlos@automantec.com",
                                new Enterprise("AutoMantec S.A.","Barranquilla","6586452","54632178-1"),
                                "Admin")
                )
        );
        transactionsList.add(
                new Transaction(-65000,
                        "Retiro 01/09/22",
                        "retiro",
                        new Employee(
                                "Alexander Carvajal",
                                "alexander@automantec.com",
                                new Enterprise("AutoMantec S.A.","Barranquilla","6586452","54632178-1"),
                                "Operario")
                )
        );
        transactionsList.add(
                new Transaction(6000000,
                        "Consignacion inicial",
                        "consignacion",
                        new Employee(
                                "Matthew Zuñiga",
                                "matthew@jzgdevelopers.com",
                                new Enterprise("JZG Developers","Cartagena","6613524","1143343653-0"),
                                "Admin")
                )
        );
        transactionsList.add(
                new Transaction(586200,
                        "Consignacion inicial",
                        "consignacion",
                        new Employee(
                                "Juan Zuñiga",
                                "juan@jzgdevelopers.com",
                                new Enterprise("JZG Developers","Cartagena","6613524","1143343653-0"),
                                "Operario")
                )
        );
        */
        transactionsList.add(new Transaction(20000,"Consignacion inicial","consignacion",employeeService.getEmployees(0)));
        transactionsList.add(new Transaction(-10000,"Retiro 30/08/22","retiro",employeeService.getEmployees(0)));
        transactionsList.add(new Transaction(1000000,"Consignacion inicial","consignacion",employeeService.getEmployees(1)));
        transactionsList.add(new Transaction(-65000,"Retiro 30/08/22","retiro",employeeService.getEmployees(1)));
        transactionsList.add(new Transaction(-65000,"Retiro 01/09/22","retiro",employeeService.getEmployees(1)));
        //transactionsList.add(new Transaction(65240000,"Consignacion inicial","consignacion",employeeService.getEmployees(4)));
        //transactionsList.add(new Transaction(-5862000,"Retiro 02/09/22","retiro",employeeService.getEmployees(4)));

    }

    // Metodo que retorna un objeto de tipo transaccion segun su index
    public Transaction getTransactions(int index){
        return transactionsList.get(index);
    }

    // Metodo que retorna el arraylist de todas las transacciones
    public ArrayList<Transaction> getTransactionsList(){
        return transactionsList;
    }

    // Metodo que retorna la informacion de una transaccion del listado segun su index
    public Transaction searchTransaction(int index){
        return transactionsList.get(index);
    }

    // Metodo que crea una transaccion y la añade al listado. Retorna un mensaje
    public String createTransaction(Transaction t){
        transactionsList.add(t);
        return "--> Transaccion creada";
    }

    // Metodo que actualiza la informacion de una transaccion segun su index. Retorna un mensaje
    public String updateTransaction(int index, Transaction t){
        transactionsList.set(index, t);
        return "--> Transaccion actualizada";
    }

    // Metodo que elimina una transaccion del listado. Retorna un mensaje
    public String deleteTransaction(int index){
        transactionsList.remove(index);
        return "--> Transaccion eliminada";
    }

}
