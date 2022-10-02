package com.innovedcol.ecofamily.controllers.ftd;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.services.frontend.EmpFEService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class GnrlFEController {

    private final EmpFEService employeeService;

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal OidcUser principal)
    {
        Employee currentUser;
        if (principal != null){

            currentUser = employeeService.createOrValidateUser(principal.getClaims());

            model.addAttribute("nameUser", currentUser.getName());
            model.addAttribute("emailUser", currentUser.getEmail());
            model.addAttribute("imgUser", currentUser.getImage());
            model.addAttribute("roleUser", currentUser.getRole().toString());
        }
        return "index";
    }
}
