package com.innovedcol.ecofamily.controllers.ftd;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class GnrlFEController {

    @RequestMapping(value= {"/index","/"})
    public String index(Model model, @AuthenticationPrincipal OidcUser principal) {
        return "index";
    }


}
