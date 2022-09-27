package br.senac.tads.dsw.exemplos.springmvc;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemplos")
public class ExemploController {

    @GetMapping
    public String ex01() {
        return "exemplo01";
    }

    @GetMapping("/ex02")
    public String ex02(Model model) {
        String titulo = "Exemplo 02 - Título gerado no Controller";
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        model.addAttribute("tituloAttr", titulo);
        model.addAttribute("dataHoraAtualAttr", dataHoraAtual);
        return "exemplo02";
    }

    @GetMapping("/ex02b")
    public ModelAndView ex02b() {
        String titulo = "Exemplo 02 - Uso do ModelAndView";
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        ModelAndView mv = new ModelAndView("exemplo02");
        mv.addObject("tituloAttr", titulo);
        mv.addObject("dataHoraAtualAttr", dataHoraAtual);
        return mv;
    }

    @GetMapping("/exjson01")
    @ResponseBody
    public String exJson01() {
        String titulo = "Exemplo JSON 01";
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        return "{ \"titulo\": \"" + titulo 
                + "\", \"dataHoraAtual\": \"" + dataHoraAtual 
                + "\" }";
    }
    
    @GetMapping("/exjson02")
    @ResponseBody
    public Dados exJson02() {
        String titulo = "Exemplo JSON 02 com Classe Dados";
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Dados d = new Dados(titulo, 123, dataHoraAtual);
        return d;
    }
    
    @GetMapping("/ex03")
    public ModelAndView ex03(
            @RequestParam("titulo") String titulo,
            @RequestParam("numero") int numero) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        ModelAndView mv = new ModelAndView("exemplo03");
        mv.addObject("titulo", "Exemplo 03 - " + titulo);
        mv.addObject("numero", numero);
        mv.addObject("dataHoraAtual", dataHoraAtual);
        return mv;
    }
    
    @GetMapping("/ex03b")
    public ModelAndView ex03b(
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "codigo", required = false) String codigo) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        ModelAndView mv = new ModelAndView("exemplo03");
        mv.addObject("titulo", "Exemplo 03 - " + titulo);
        mv.addObject("numero", numero);
        mv.addObject("dataHoraAtual", dataHoraAtual);
        mv.addObject("codigo", codigo);
        return mv;
    }
    
    @GetMapping("/ex04")
    public ModelAndView ex04(
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "codigo", required = false) String codigo) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        ModelAndView mv = new ModelAndView("exemplo04");
        Dados d = new Dados(titulo, numero, dataHoraAtual, codigo);
        mv.addObject("dados", d);
        return mv;
    }

}
