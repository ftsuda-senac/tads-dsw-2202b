package br.senac.tads.dsw.exemplospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarFormLogin() {
        return "login";
    }

}
