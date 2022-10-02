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
            model.addAttribute("phoneUser", currentUser.getPhone());
            if (currentUser.getEnterprise()!=null){
                model.addAttribute("idEntUser", currentUser.getEnterprise().getId());
            }else{
                model.addAttribute("idEntUser", null);
            }
            model.addAttribute("enterpriseUser", currentUser.getEnterprise());

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
                return "new_enterprise";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/enterprise/new/go")
    public String createEnterprise(@ModelAttribute("empresa") Enterprise e, RedirectAttributes attributes, @AuthenticationPrincipal OidcUser principal){
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

    @GetMapping("/enterprise/update/{id}")
    public String formActualizarEmpresa(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal){
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
                model.addAttribute("empresa",enterpriseService.searchEnterprise(id).get());
                return "update_enterprise";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/";
        }
    }
    @PostMapping("/enterprise/update/go/{id}")
    public String updateEnterprise(@PathVariable("id") Long id, @ModelAttribute("empresa") Enterprise e, RedirectAttributes attributes, @AuthenticationPrincipal OidcUser principal){
        if (principal != null){
            String result = this.enterpriseService.updateEnterprise(id,e);
            if(result.equals("--> Empresa actualizada con éxito!")) {
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