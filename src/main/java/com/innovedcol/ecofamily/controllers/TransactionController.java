package com.innovedcol.ecofamily.controllers;


import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TransactionController {

    TransactionService service;

    public TransactionController() {
        this.service = new TransactionService();
    }

    @GetMapping("/listMovements")

    public ArrayList <Transaction> listMovements (){
        return this.service.getTransactionsList();
    }

    @GetMapping("/movements/{index}")
    public Transaction searchTransaction(@PathVariable("index") Integer index) {
     return this.service.searchTransaction(index);
    }
    @PostMapping("/create")
    public String createTransaction(@RequestBody Transaction t){
        return this.service.createTransaction(t);
    }
    @PatchMapping("/Update/{index}")
    public String updateTransaction(@PathVariable("index") Integer index, @RequestBody Transaction t){
    return this.service.updateTransaction(index,t);
    }
    @DeleteMapping("/delete/{index}")
    public String deleteTransaction(@PathVariable ("index") Integer index) {
        return this.service.deleteTransaction(index);
    }


}
