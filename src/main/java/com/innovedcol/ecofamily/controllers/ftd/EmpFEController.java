package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.enums.EnumRoleEmployee;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import com.innovedcol.ecofamily.services.frontend.EntFEService;
import lombok.AllArgsConstructor;
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
    public String usersIndex(Model model) {
        List<?> listaEmpleados = this.employeeService.getEmployeesList();
        if (listaEmpleados.size()==1 && listaEmpleados.get(0).toString().equals("No existen empleados")){
            model.addAttribute("hayEmpleados",false);
        }else {
            model.addAttribute("hayEmpleados",true);
            model.addAttribute("listaEmpleados", listaEmpleados);
        }
        return "users";
    }

    @GetMapping("/user/new")
    public String formNuevoEmpleado(Model model){
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
        return "new_user";
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

    @GetMapping("/user/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/users";
    }

    // Método para llamar al servicio que busca las transacciones de una empresa de acuerdo a su id:
    @GetMapping("/user/{id}/movements")
    public String searchTransactionsEmployee(@PathVariable("id") Long id, Model model){
        List<?> transactions = this.employeeService.searchTransactionsEmployee(id);
        if (transactions.size()==1 && transactions.get(0).toString().equals("Empleado no existe")){
            model.addAttribute("userConTransacciones",false);
        }else {
            model.addAttribute("userConTransacciones",true);
            model.addAttribute("listaTransaccionesUser", transactions);
        }
        return "user_transactions";
    }

}
