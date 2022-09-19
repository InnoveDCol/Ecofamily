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
    public List <?> listMovements (){
        return this.service.getTransactionsList();
    }

    /*
    @PostMapping("/movements")

    public String createTransaction(@RequestBody Transaction t){
        return this.service.createTransaction(t);
    }
    */
    @PostMapping("/movements/{usr_id}/{ent_id}")
    public String createTransaction(@PathVariable("usr_id") Long usr_id, @PathVariable("ent_id") Long ent_id, @RequestBody Transaction t){
        return this.service.createTransaction(usr_id, ent_id,t);
    }

    @GetMapping("/movement/{id}")
    public Optional< Transaction> searchTransaction(@PathVariable("id") Long id) {
     return this.service.searchTransaction(id);
    }

    @PatchMapping("/movement/{id}")
    public String updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction t){
    return this.service.updateTransaction(id,t);
    }

    @DeleteMapping("/movement/{id}")
    public String deleteTransaction(@PathVariable ("id") Long id) {
        return this.service.deleteTransaction(id);
    }


}
