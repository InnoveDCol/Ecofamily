package com.innovedcol.ecofamily.controllers;

import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TransactionController {

    TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/movements")
    public ArrayList <Transaction> listMovements (){
        return this.service.getTransactionsList();
    }

    @PostMapping("/movements")
    public String createTransaction(@RequestBody Transaction t){
        return this.service.createTransaction(t);
    }

    @GetMapping("/movements/{id}")
    public Optional< Transaction> searchTransaction(@PathVariable("id") Long id) {
     return this.service.searchTransaction(id);
    }

    @PatchMapping("/movements/{id}")
    public String updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction t){
    return this.service.updateTransaction(id,t);
    }

    @DeleteMapping("/movements/{id}")
    public String deleteTransaction(@PathVariable ("id") Long id) {
        return this.service.deleteTransaction(id);
    }


}
