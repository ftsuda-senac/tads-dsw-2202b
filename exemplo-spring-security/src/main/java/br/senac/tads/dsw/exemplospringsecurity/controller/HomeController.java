package br.senac.tads.dsw.exemplospringsecurity.controller;

import br.senac.tads.dsw.exemplospringsecurity.dominio.UsuarioSistema;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView mostrarHome(Authentication auth) {
        UsuarioSistema usuario = null;
        if (auth != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }
        return new ModelAndView("home").addObject("usuario", usuario);
    }

}
