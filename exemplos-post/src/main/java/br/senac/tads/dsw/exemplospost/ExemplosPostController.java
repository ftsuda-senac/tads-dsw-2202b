package br.senac.tads.dsw.exemplospost;

import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/exemplos-post")
public class ExemplosPostController {
    
    @GetMapping("/abrir-form")
    public ModelAndView abrirForm() {
        ModelAndView mv = new ModelAndView("formulario");
        return mv;
    }
    
    
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
    
    @PostMapping("/receber-dados")
    public ModelAndView receberDados2(@ModelAttribute DadosPessoais dp) {
        ModelAndView mv = new ModelAndView("resultados");
        mv.addObject("dados", dp);
        return mv;
    }
    
    // ===== Exemplos POST-REDIRECT-GET (PRG)
    
    @GetMapping("/abrir-form-prg")
    public ModelAndView mostrarFormPrg() {
        return new ModelAndView("formulario-prg");
    }
    
    @PostMapping("/receber-dados-prg")
    public ModelAndView receberDadosPrg(
            @ModelAttribute DadosPessoais dp, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("dados", dp);
        redirectAttributes.addFlashAttribute("origemPost", "POST-Redirect-GET");
        ModelAndView mv = new ModelAndView("redirect:/exemplos-post/resultado");
        return mv;
    }
    
    @GetMapping("/resultado")
    public ModelAndView mostrarResultado() {
        ModelAndView mv = new ModelAndView("resultado");
        return mv;
    }
    
    // ======= Exemplos validação de dados
    @GetMapping("/abrir-form-validacao")
    public ModelAndView mostrarFormValidacao() {
        ModelAndView mv = new ModelAndView("formulario-validacao");
        mv.addObject("dados", new DadosPessoais());
        return mv;
    }
    
    @PostMapping("/receber-dados-validacao")
    public ModelAndView receberDadosValidacao(
            @ModelAttribute("dados") @Valid DadosPessoais dp,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("formulario-validacao");
            return mv;
        }
        redirectAttributes.addFlashAttribute("dados", dp);
        redirectAttributes.addFlashAttribute("origemPost", "Validação");
        ModelAndView mv = new ModelAndView("redirect:/exemplos-post/resultado");
        return mv;
    }
    
}
