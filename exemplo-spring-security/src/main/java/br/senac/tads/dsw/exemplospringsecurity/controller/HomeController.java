package br.senac.tads.dsw.exemplospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView mostrarHome() {
        return new ModelAndView("home").addObject("usuario", null);
    }

}
