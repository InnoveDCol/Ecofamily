package com.innovedcol.ecofamily.controllers;


import com.innovedcol.ecofamily.services.EmployeeService;
import com.innovedcol.ecofamily.services.EnterpriseService;
import com.innovedcol.ecofamily.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FrontController {
    EmployeeService service;
    TransactionService transactionService;
    EnterpriseService enterpriseService;

    public FrontController(EmployeeService service) {
        this.service = service;
    }
    public FrontController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public FrontController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/users")
    public String users(Model model){
        List<?> users= this.service.getEmployeesList();
        model.addAttribute("users", users);
        return "cuarta";
    }
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String nuevoUsuario (){
        return "quinta";
    }
    @RequestMapping("/")
    public String index (){
        return "primera";
    }

    @GetMapping("/Transacciones")
    public String transacciones(Model model){
        List<?> transaccion =this.transactionService.getTransactionsList();
        model.addAttribute("transacciones", transaccion);
        return "segunda";
    }
    @RequestMapping(value = "/transacciones", method = RequestMethod.GET)
    public String nuevoMovimientoDeDinero(){
        return "tercera";
    }
    @GetMapping("/Empresas")
    public String empresa(Model model){
        List<?> empresa=this.service.getEmployeesList();
        model.addAttribute("empresa", empresa);
        return "sexta";
    }
    @RequestMapping(value = "/empresa", method = RequestMethod.GET)
    public String nuevaEmpresa(){
        return"septima";
    }
    }







