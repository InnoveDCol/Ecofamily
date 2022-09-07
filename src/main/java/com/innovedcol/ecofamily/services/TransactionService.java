package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    // Definimos un atributo de tipo repositorio
    private final TransactionRepository repository;

    // Constructor
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    // Método que retorna un arraylist con el listado de las transacciones
    public ArrayList<Transaction> getTransactionsList(){
        return (ArrayList<Transaction>) repository.findAll();
    }

    // Método que retorna un objeto de tipo Transaction según su ID
    public Optional<Transaction> searchTransaction(Long id){
        return repository.findById(id);
    }

    // Método que crea una transacción y la añade a la base de datos. Retorna un mensaje
    public String createTransaction(Transaction t){
        if(searchTransaction(t.getId()).isEmpty()){
            repository.save(t);
            return "--> Transacción creada con éxito!";
        }else{
            return "--> Transacción ya existe!";
        }
    }

    // Método que actualiza la información de una transacción según su id. Retorna un mensaje
    public String updateTransaction(Long id, Transaction t){
        if(searchTransaction(id).isPresent()){
            repository.save(t);
            return "--> Transacción actualizada con éxito!";
        }else{
            return "--> La transacción indicada no existe!";
        }
    }

    // Método que elimina una transacción de la base de datos. Retorna un mensaje
    public String deleteTransaction(Long id){
        if(searchTransaction(id).isPresent()){
            repository.deleteById(id);
            return "--> Transacción eliminada con éxito!";
        }else{
            return "--> La transacción indicada no existe!";
        }
    }
}