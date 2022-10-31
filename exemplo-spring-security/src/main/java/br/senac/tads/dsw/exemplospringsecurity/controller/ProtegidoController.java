package br.senac.tads.dsw.exemplospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protegido")
public class ProtegidoController {

    @GetMapping("/peao")
    public ModelAndView mostrarPeaoPage() {
        return new ModelAndView("protegido").addObject("titulo", "Página do PEAO").addObject("msg",
                "Usuário logado possui papel \"PEAO\"");
    }

    @GetMapping("/fodon")
    public ModelAndView mostrarFodonPage() {
        return new ModelAndView("protegido").addObject("titulo", "Página do FODON").addObject("msg",
                "Usuário logado possui papel \"FODON\"");
    }

    @GetMapping("/god")
    public ModelAndView mostrargODPage() {
        return new ModelAndView("protegido").addObject("titulo", "Página do GOD").addObject("msg",
                "Usuário logado possui papel \"GOD\"");
    }

}
