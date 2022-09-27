package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.enums.EnumTypeTransaction;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import com.innovedcol.ecofamily.services.frontend.EntFEService;
import com.innovedcol.ecofamily.services.frontend.TranFEService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@AllArgsConstructor
public class TranFEController {

    private final TranFEService transactionService;
    private final EntFEService enterpriseService;
    private final EmpFEService employeeService;



    @RequestMapping("/transactions")
    public String transactionsIndex(Model model, Locale locale) {
        List<?> listaTransacciones = this.transactionService.getTransactionsList();

        if (listaTransacciones.size()==1 && listaTransacciones.get(0).toString().equals("No existen transacciones")){
            model.addAttribute("hayTransacciones",false);
        }else {
            model.addAttribute("hayTransacciones",true);
            model.addAttribute("listaTransacciones",listaTransacciones);
        }
        return "transactions";
    }

    @GetMapping("/transaction/new")
    public String formNuevaTransaccion(Model model){
        List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
        List<?> listaUsuarios = this.employeeService.getEmployeesList();
        List<EnumTypeTransaction> listaTipoTransacciones = new ArrayList<EnumTypeTransaction>(Arrays.asList(EnumTypeTransaction.values()));
        if (listaUsuarios.size()==1 && listaUsuarios.get(0).toString().equals("No existen empleados")){
            model.addAttribute("hayUsuarios",false);
        }else {
            model.addAttribute("hayUsuarios",true);
            model.addAttribute("listaEmpresas",listaEmpresas);
            model.addAttribute("listaUsuarios",listaUsuarios);
        }

        model.addAttribute("listaTipoTransacciones",listaTipoTransacciones);
        model.addAttribute("transaccion",new Transaction());
        return "new_transaction";
    }

    @PostMapping("/transaction/new/go/{usr_id}/{ent_id}")
    //@PostMapping("/transaction/new/go")
    public String createTransaction(@PathVariable("usr_id") Long usr_id, @PathVariable("ent_id") Long ent_id, @ModelAttribute("transaccion") Transaction t, Model model, RedirectAttributes attributes){
    //public String createTransaction(@ModelAttribute("transaccion") Transaction t, Model model, RedirectAttributes attributes){
        //Long usr_id = t.getEmployee().getId();
        //Long ent_id = t.getEnterprise().getId();
        String result = this.transactionService.createTransaction(usr_id,ent_id,t);
        if(result.equals("--> Transacción creada con éxito!")) {
            attributes.addFlashAttribute("mensajeOk",result);
        }else{
            attributes.addFlashAttribute("error",result);
        }
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable("id") Long id, RedirectAttributes attributes) {

        String result = this.transactionService.deleteTransaction(id);
        if(result.equals("--> Transacción eliminada con éxito!")) {
            attributes.addFlashAttribute("mensajeOk",result);
        }else{
            attributes.addFlashAttribute("error",result);
        }
        return "redirect:/transactions";
    }


}
