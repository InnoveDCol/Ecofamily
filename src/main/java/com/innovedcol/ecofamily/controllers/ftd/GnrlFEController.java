package com.innovedcol.ecofamily.controllers.ftd;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class GnrlFEController {

    @RequestMapping(value= {"/index","/"})
    public String index() {
        return "index";
    }

}
