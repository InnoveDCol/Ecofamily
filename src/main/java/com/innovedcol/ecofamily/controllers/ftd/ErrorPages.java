package com.innovedcol.ecofamily.controllers.ftd;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPages implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "404";
    }
}