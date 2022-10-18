/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplosspring.sessao;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.senac.tads.dsw.exemplosspring.sessao.item.Item;
import br.senac.tads.dsw.exemplosspring.sessao.item.ItemService;

@Controller
@RequestMapping("/exemplo-sessao2")
public class ExemploSessaoController2 {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView mostrarTela() {
        return new ModelAndView("exemplo-sessao2").addObject("itens",
                itemService.findAll());
    }

    @PostMapping
    public ModelAndView adicionarItem(
            @ModelAttribute("itemId") Integer itemId,
            RedirectAttributes redirAttr,
            HttpServletRequest servletReq
    ) {
        HttpSession sessao = servletReq.getSession();
        if (sessao.getAttribute("itensSelecionados2") == null) {
            sessao.setAttribute("itensSelecionados2", new ArrayList<ItemSelecionado>());
        }
        List<ItemSelecionado> itensSelecionados = 
                (ArrayList<ItemSelecionado>) sessao.getAttribute("itensSelecionados2");
        
        Item item = itemService.findById(itemId);
        itensSelecionados.add(
                new ItemSelecionado(item,
                        servletReq.getHeader("user-agent"),
                        servletReq.getRemoteAddr()));
        redirAttr.addFlashAttribute("msg", "Item ID " + item.getId() + " adicionado com sucesso");
        return new ModelAndView("redirect:/exemplo-sessao2");
    }

    @GetMapping("/limpar")
    public ModelAndView limparSelecionados(HttpSession sessao,
            RedirectAttributes redirAttr) {
        if (sessao.getAttribute("itensSelecionados2") != null) {
            List<ItemSelecionado> itensSelecionados =
                    (ArrayList<ItemSelecionado>) sessao.getAttribute("itensSelecionados2");
            itensSelecionados.clear();
        }
        redirAttr.addFlashAttribute("msg", "Itens removidos");
        return new ModelAndView("redirect:/exemplo-sessao2");
    }

    @ModelAttribute("titulo")
    public String getTitulo() {
        return "Exemplo Sessao 2 - Uso do HttpSession do HttpServletRequest";
    }
}