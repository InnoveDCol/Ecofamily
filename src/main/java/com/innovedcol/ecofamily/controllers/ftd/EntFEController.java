package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import com.innovedcol.ecofamily.services.frontend.EntFEService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class EntFEController {
private final EntFEService enterpriseService;
private final EmpFEService employeeService;

    @RequestMapping("/enterprise")
    public String enterprisesIndex(Model model, @AuthenticationPrincipal OidcUser principal) {
        Employee currentUser;
        if (principal != null){

            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            if (listaEmpresas.size()==1 && listaEmpresas.get(0).toString().equals("No existen empresas")){
                //attributes.addFlashAttribute("hayEmpresas",true);
                model.addAttribute("hayEmpresas",false);
            }else {
                model.addAttribute("hayEmpresas",true);
                model.addAttribute("listaEmpresas",listaEmpresas);
            }

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);

            if (roleActual.equals("Admin")){
                return "enterprises";
            }else{
                return "redirect:/";
            }

        }else{
            return "redirect:/";
        }


    }

    @GetMapping("/enterprise/new")
    public String formNuevaEmpresa(Model model, @AuthenticationPrincipal OidcUser principal){
        /*if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            if (listaEmpresas.size()>0 && !listaEmpresas.get(0).toString().equals("No existen empresas")){
                model.addAttribute("hayEmpresas",true);
            }else {
                model.addAttribute("hayEmpresas",false);
                model.addAttribute("empresa",new Enterprise());
            }

            model.addAttribute("nameUser", principal.getFullName());
            model.addAttribute("emailUser", principal.getEmail());
            model.addAttribute("phoneUser", principal.getPhoneNumber());
            model.addAttribute("imgUser", principal.getPicture());

            return "new_enterprise";
        }else{
            return "redirect:/";
        }*/
        Employee currentUser;
        if (principal != null){
            List<?> listaEmpresas = this.enterpriseService.getEnterprisesList();
            if (listaEmpresas.size()>0 && !listaEmpresas.get(0).toString().equals("No existen empresas")){
                model.addAttribute("hayEmpresas",true);
            }else {
                model.addAttribute("hayEmpresas",false);
                model.addAttribute("empresa",new Enterprise());
            }

            currentUser = employeeService.createOrValidateUser(principal.getClaims());
            String roleActual = currentUser.getRole().toString();
            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("phoneUser", currentUser.getPhone());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", roleActual);

            if (roleActual.equals("Admin")){
                return "new_enterprise";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/enterprise/new/go")
    public String createEnterprise(@ModelAttribute("empresa") Enterprise e, Model model, RedirectAttributes attributes, @AuthenticationPrincipal OidcUser principal){
        if (principal != null){
            String result = this.enterpriseService.createEnterprise(e);
            if(result.equals("--> Empresa creada con éxito!")) {
                attributes.addFlashAttribute("mensajeOk",result);
            }else{
                attributes.addFlashAttribute("error",result);
            }
            return "redirect:/enterprise";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/enterprise/delete/{id}")
    public String deleteEnterprise(@PathVariable("id") Long id, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null){
            this.enterpriseService.deleteEnterprise(id);
            return "redirect:/enterprise";
        }else{
            return "redirect:/";
        }
    }
}