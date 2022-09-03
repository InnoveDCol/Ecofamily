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

        transactionsList.add(new Transaction(20000,"Consignacion inicial","consignacion",employeeService.searchEmployee(0)));
        transactionsList.add(new Transaction(-10000,"Retiro 30/08/22","retiro",employeeService.searchEmployee(0)));
        transactionsList.add(new Transaction(1000000,"Consignacion inicial","consignacion",employeeService.searchEmployee(1)));
        transactionsList.add(new Transaction(-65000,"Retiro 30/08/22","retiro",employeeService.searchEmployee(1)));
        transactionsList.add(new Transaction(-65000,"Retiro 01/09/22","retiro",employeeService.searchEmployee(1)));
        transactionsList.add(new Transaction(65240000,"Consignacion inicial","consignacion",employeeService.searchEmployee(4)));
        transactionsList.add(new Transaction(-5862000,"Retiro 02/09/22","retiro",employeeService.searchEmployee(4)));

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
