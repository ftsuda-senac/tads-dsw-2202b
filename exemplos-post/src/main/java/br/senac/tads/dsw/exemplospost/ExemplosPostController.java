package br.senac.tads.dsw.exemplospost;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemplos-post")
public class ExemplosPostController {
    
    @GetMapping("/abrir-form")
    public ModelAndView abrirForm() {
        ModelAndView mv = new ModelAndView("formulario");
        return mv;
    }
    
    @PostMapping("/receber-dados")
    public ModelAndView receberDados(
            @RequestParam String nome,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String senha) {
        
        DadosPessoais dp = new DadosPessoais();
        dp.setNome(nome);
        dp.setDataNascimento(dataNascimento);
        dp.setEmail(email);
        dp.setTelefone(telefone);
        dp.setSenha(senha);
        
        ModelAndView mv = new ModelAndView("resultados");
        mv.addObject("dados", dp);
        return mv;
    }
    
}
