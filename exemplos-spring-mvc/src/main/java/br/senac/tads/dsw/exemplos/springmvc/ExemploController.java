package br.senac.tads.dsw.exemplos.springmvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    @GetMapping("/ex04b")
    public ModelAndView ex04b() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        Dados d1 = new Dados("Item 1", 12, dataHoraAtual, "cod111");
        Dados d2 = new Dados("Item 2 XXXXXX", 39, dataHoraAtual, "cod23123");
        Dados d3 = new Dados("Item XPTO", 59, dataHoraAtual, "cod12121");
        
        List<Dados> listaDados = new ArrayList<>();
        listaDados.add(d1);
        listaDados.add(d2);
        listaDados.add(d3);
        // Adicionando 4o item
        listaDados.add(new Dados("Item 4", 88, dataHoraAtual, "codigo4444"));
        
        ModelAndView mv = new ModelAndView("exemplo04b");
        mv.addObject("listaDados", listaDados);
        return mv;
    }
    
    @GetMapping("/ex05/{apelido}")
    public ModelAndView ex05(@PathVariable String apelido,
            @RequestParam(defaultValue = "0") int numero,
            @RequestParam(required = false) String codigo) {
        
        String titulo = "Apelido não encontrado";
        if ("fulano".equalsIgnoreCase(apelido)) {
            titulo = "Página do Fulano da Silva";
        } else if ("ciclano".equalsIgnoreCase(apelido)) {
            titulo = "Página do Ciclano de Souza";
        } else if ("beltrana".equalsIgnoreCase(apelido)) {
            titulo = "Página da Beltrana dos Santos";
        }
        
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        ModelAndView mv = new ModelAndView("exemplo05");
        Dados d = new Dados(titulo, numero, dataHoraAtual, codigo);
        mv.addObject(d);
        return mv;
    }
    
    @GetMapping("/ex-exibicao-dinamica-v1")
    public ModelAndView exExibicaoDinamica(@RequestHeader("user-agent") String userAgent) {
        String mensagem = "Acesso via dispositivo movel (Versão Spring MVC)";
        String backgrondColor = "#2ecc71"; //Verde
        if (!userAgent.toLowerCase().contains("mobile")) {
            mensagem = "Acesso via desktop (Versão Spring MVC)";
            backgrondColor = "#9b59b6"; // Roxo
        }
        ModelAndView mv = new ModelAndView("ex-exibicao-dinamica-v1");
        mv.addObject("backgroundColor", backgrondColor);
        mv.addObject("mensagem", mensagem);
        mv.addObject("userAgent", userAgent);
        return mv;
    }
    
    @GetMapping("/ex-exibicao-dinamica-v2")
    public ModelAndView exExibicaoDinamicaV2(@RequestHeader("user-agent") String userAgent) {
        String template = "ex-exibicao-dinamica-mobile";
        if (!userAgent.toLowerCase().contains("mobile")) {
            template = "ex-exibicao-dinamica-desktop";
        }
        ModelAndView mv = new ModelAndView(template);
        mv.addObject("userAgent", userAgent);
        return mv;
    }
    
    @GetMapping("/ex-sites-separados/desktop")
    public ModelAndView exSitesSeparadosDesktop(
            @RequestHeader("user-agent") String userAgent) {
        if (userAgent.toLowerCase().contains("mobile")) {
            ModelAndView mv = new ModelAndView(
                    "redirect:/exemplos/ex-sites-separados/mobile");
            return mv;
        }
        ModelAndView mv = new ModelAndView("ex-sites-separados-desktop");
        mv.addObject("userAgent", userAgent);
        return mv;
    }
    
    @GetMapping("/ex-sites-separados/mobile")
    public ModelAndView exSitesSeparadosMobile(
            @RequestHeader("user-agent") String userAgent) {
        if (!userAgent.toLowerCase().contains("mobile")) {
          ModelAndView mv = new ModelAndView(
                    "redirect:/exemplos/ex-sites-separados/desktop");
            return mv;
        }
        ModelAndView mv = new ModelAndView("ex-sites-separados-mobile");
        mv.addObject("userAgent", userAgent);
        return mv;
    }
    
    @GetMapping("/ex-headers")
    public ModelAndView exHeaders(@RequestHeader Map<String, String> mapHeaders,
            @RequestParam(defaultValue = "list") String tipoView) {
        String template = "ex-headers-list";
        if ("table".equals(tipoView)) {
            template = "ex-headers-table";
        }
        
        ModelAndView mv = new ModelAndView(template);
        mv.addObject("headers", mapHeaders);
        return mv;
    }

}
 