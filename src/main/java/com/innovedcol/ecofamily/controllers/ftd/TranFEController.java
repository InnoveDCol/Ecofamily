package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.enums.EnumTypeTransaction;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import com.innovedcol.ecofamily.services.frontend.EntFEService;
import com.innovedcol.ecofamily.services.frontend.TranFEService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public String transactionsIndex(Model model, Locale locale, @AuthenticationPrincipal OidcUser principal) {
        Employee currentUser;
        if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            List<?> listaUsuarios = this.employeeService.getEmployeesList();
            boolean hayEmpresas = false, hayUsuarios = false;
            if (listaEmpresas.size()>0 && !listaEmpresas.get(0).toString().equals("No existen empresas")){
                hayEmpresas = true;
                if (listaUsuarios.size()==1 && listaUsuarios.get(0).toString().equals("No existen empleados")){
                    hayUsuarios = false;
                }else {
                    hayUsuarios = true;
                }
            }else {
                hayEmpresas = false;
            }

            double totalIngresos=0, totalEgresos=0, totalTransacciones=0;
            List<?> listaTransacciones = this.transactionService.getTransactionsList();
            if (listaTransacciones.size()==1 && listaTransacciones.get(0).toString().equals("No existen transacciones")){
                model.addAttribute("hayTransacciones",false);
            }else {
                for (Transaction tx : (List<Transaction>) listaTransacciones){
                    if(tx.getType().toString().equals("Ingreso")){
                        totalIngresos += tx.getAmount();
                    }
                    if(tx.getType().toString().equals("Egreso")){
                        totalEgresos += tx.getAmount();
                    }
                }
                totalTransacciones = totalIngresos - totalEgresos;
                model.addAttribute("hayTransacciones",true);
                model.addAttribute("listaTransacciones",listaTransacciones);
                model.addAttribute("totalTransacciones",totalTransacciones);
            }

            model.addAttribute("hayUsuarios",hayUsuarios);
            model.addAttribute("hayEmpresas",hayEmpresas);

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);

            return "transactions";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/transaction/new")
    public String formNuevaTransaccion(Model model, @AuthenticationPrincipal OidcUser principal){

        Employee currentUser;
        if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            List<?> listaUsuarios = this.employeeService.getEmployeesList();
            List<EnumTypeTransaction> listaTipoTransacciones = new ArrayList<EnumTypeTransaction>(Arrays.asList(EnumTypeTransaction.values()));

            boolean hayEmpresas = false, hayUsuarios = false;


            if (listaEmpresas.size()>0 && !listaEmpresas.get(0).toString().equals("No existen empresas")){
                if (listaUsuarios.size()==1 && listaUsuarios.get(0).toString().equals("No existen empleados")){
                    //model.addAttribute("hayUsuarios",false);
                    hayUsuarios = false;
                }else {
                    hayUsuarios = true;
                    //model.addAttribute("hayUsuarios",true);
                    model.addAttribute("listaEmpresas",listaEmpresas);
                    model.addAttribute("listaUsuarios",listaUsuarios);
                }
                hayEmpresas = true;
                //model.addAttribute("hayEmpresas",true);
                model.addAttribute("listaTipoTransacciones",listaTipoTransacciones);
                model.addAttribute("transaccion",new Transaction());
            }else {
                hayEmpresas = false;
                //model.addAttribute("hayEmpresas",false);
            }

            model.addAttribute("hayUsuarios",hayUsuarios);
            model.addAttribute("hayEmpresas",hayEmpresas);

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("idUser", currentUser.getId());
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);
            model.addAttribute("idEntUser", currentUser.getEnterprise().getId());

            return "new_transaction";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/transaction/new/go/{usr_id}/{ent_id}")
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
    public String deleteTransaction(@PathVariable("id") Long id, RedirectAttributes attributes, @AuthenticationPrincipal OidcUser principal) {

        if (principal != null){
            String result = this.transactionService.deleteTransaction(id);
            if(result.equals("--> Transacción eliminada con éxito!")) {
                attributes.addFlashAttribute("mensajeOk",result);
            }else{
                attributes.addFlashAttribute("error",result);
            }
            return "redirect:/transactions";
        }else{
            return "redirect:/";
        }
    }
}