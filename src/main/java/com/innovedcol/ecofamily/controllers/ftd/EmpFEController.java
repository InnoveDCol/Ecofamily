package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Transaction;
import com.innovedcol.ecofamily.enums.EnumRoleEmployee;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import com.innovedcol.ecofamily.services.frontend.EntFEService;
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

@Controller
@AllArgsConstructor
public class EmpFEController {

    private final EmpFEService employeeService;
    private final EntFEService enterpriseService;

    @RequestMapping("/users")
    public String usersIndex(Model model, @AuthenticationPrincipal OidcUser principal) {

        Employee currentUser;

        if (principal != null){

            List<?> listaEmpleados = this.employeeService.getEmployeesList();
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();

            if (listaEmpresas.size()==1 && listaEmpresas.get(0).toString().equals("No existen empresas")){
                model.addAttribute("hayEmpresas",false);
            }else {
                model.addAttribute("hayEmpresas",true);
            }

            if (listaEmpleados.size()==1 && listaEmpleados.get(0).toString().equals("No existen empleados")){
                model.addAttribute("hayEmpleados",false);
            }else {
                model.addAttribute("hayEmpleados",true);
                model.addAttribute("listaEmpleados", listaEmpleados);
            }

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);
            model.addAttribute("phoneUser", currentUser.getPhone());
            model.addAttribute("enterpriseUser", currentUser.getEnterprise());

            return "users";
        }else{
            return "redirect:/";
        }
    }


    @GetMapping("/user/new")
    public String formNuevoEmpleado(Model model, @AuthenticationPrincipal OidcUser principal){
        Employee currentUser;
        if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            List<EnumRoleEmployee> listaRoles = new ArrayList<EnumRoleEmployee>(Arrays.asList(EnumRoleEmployee.values()));
            if (listaEmpresas.size()==1 && listaEmpresas.get(0).toString().equals("No existen empresas")){
                model.addAttribute("hayEmpresas",false);
            }else {
                model.addAttribute("hayEmpresas",true);
                model.addAttribute("listaEmpresas",listaEmpresas);
            }

            model.addAttribute("listaRoles",listaRoles);
            model.addAttribute("empleado",new Employee());

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);
            model.addAttribute("phoneUser", currentUser.getPhone());
            if (currentUser.getEnterprise()!=null){
                model.addAttribute("idEntUser", currentUser.getEnterprise().getId());
            }else{
                model.addAttribute("idEntUser", null);
            }
            model.addAttribute("enterpriseUser", currentUser.getEnterprise());

            if (roleActual.equals("Admin")){
                return "new_user";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/user/new/go/{ent_id}")
    public String createEmployee(@PathVariable("ent_id") Long ent_id, @ModelAttribute("empleado") Employee e, Model model, RedirectAttributes attributes){
        String result = this.employeeService.createEmployee(ent_id, e);
        if(result.equals("--> Empleado creado con éxito!")) {
            attributes.addFlashAttribute("mensajeOk",result);
        }else{
            attributes.addFlashAttribute("error",result);
        }
        return "redirect:/users";
    }

    @GetMapping("/user/update/{id}")
    public String formActualizarEmpleado(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal){
        Employee currentUser;
        if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            List<EnumRoleEmployee> listaRoles = new ArrayList<>(Arrays.asList(EnumRoleEmployee.values()));
            if (listaEmpresas.size()==1 && listaEmpresas.get(0).toString().equals("No existen empresas")){
                model.addAttribute("hayEmpresas",false);
            }else {
                model.addAttribute("hayEmpresas",true);
                model.addAttribute("listaEmpresas",listaEmpresas);
            }

            model.addAttribute("listaRoles",listaRoles);
            model.addAttribute("empleado",new Employee());

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);
            model.addAttribute("phoneUser", currentUser.getPhone());
            if (currentUser.getEnterprise()!=null){
                model.addAttribute("idEntUser", currentUser.getEnterprise().getId());
            }else{
                model.addAttribute("idEntUser", null);
            }
            model.addAttribute("enterpriseUser", currentUser.getEnterprise());

            if (roleActual.equals("Admin")){
                model.addAttribute("empleado",employeeService.searchEmployee(id).get());
                return "update_user";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/user/update/go/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("empleado") Employee e, Model model, RedirectAttributes attributes){
        String result = this.employeeService.updateEmployee(id, e);
        if(result.equals("--> Empleado actualizado con éxito!")) {
            attributes.addFlashAttribute("mensajeOk",result);
        }else{
            attributes.addFlashAttribute("error",result);
        }
        return "redirect:/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/users";
    }

    // Método para llamar al servicio que busca las transacciones de una empresa de acuerdo a su id:
    @GetMapping("/user/{id}/movements")
    public String searchTransactionsEmployee(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal){
        Employee currentUser;

        if (principal != null){
            double totalIngresos=0, totalEgresos=0, totalTransacciones=0;
            List<?> transactions = this.employeeService.searchTransactionsEmployee(id);
            if (transactions.size()==1 && transactions.get(0).toString().equals("Empleado no existe")){
                model.addAttribute("userConTransacciones",false);
            }else {
                for (Transaction tx : (List<Transaction>) transactions){
                    if(tx.getType().toString().equals("Ingreso")){
                        totalIngresos += tx.getAmount();
                    }
                    if(tx.getType().toString().equals("Egreso")){
                        totalEgresos += tx.getAmount();
                    }
                }
                totalTransacciones = totalIngresos - totalEgresos;
                model.addAttribute("userConTransacciones",true);
                model.addAttribute("listaTransaccionesUser", transactions);
                model.addAttribute("totalTransacciones",totalTransacciones);
            }
            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);
            model.addAttribute("phoneUser", currentUser.getPhone());
            model.addAttribute("enterpriseUser", currentUser.getEnterprise());

            return "user_transactions";
        }else{
            return "redirect:/";
        }
    }
}
