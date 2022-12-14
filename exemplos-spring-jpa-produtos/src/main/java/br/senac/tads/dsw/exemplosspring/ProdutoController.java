/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senac.tads.dsw.exemplosspring.produto.Categoria;
import br.senac.tads.dsw.exemplosspring.produto.CategoriaRepository;
import br.senac.tads.dsw.exemplosspring.produto.ImagemProduto;
import br.senac.tads.dsw.exemplosspring.produto.Produto;
import br.senac.tads.dsw.exemplosspring.produto.ProdutoRepository;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ModelAndView listar(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "qtd", defaultValue = "500") int qtd,
            @RequestParam(name = "idsCat", required = false) List<Integer> idsCat) {
        List<Produto> resultados;
        if (idsCat != null && !idsCat.isEmpty()) {
            // Busca pelos IDs das categorias informadas
            //Page<Produto> pageResultados = produtoRepository.findDistinctByCategorias_IdIn(idsCat, PageRequest.of(offset/qtd, qtd));
            //resultados = pageResultados.getContent();
            resultados = produtoRepository.findByXpto(idsCat);
        } else {
            // Lista todos os produtos usando paginacao
            Page<Produto> pageResultados = produtoRepository.findAll(PageRequest.of(offset/qtd, qtd));
            resultados = pageResultados.getContent();
        }
        return new ModelAndView("produtos/lista").addObject("produtos", resultados);
    }
    
    @GetMapping("/query-by-example")
    public ModelAndView listarQueryByExample(
            @RequestParam(name = "idsCat", required = false) List<Integer> idsCat) {
        List<Produto> resultados;
        if (idsCat != null && !idsCat.isEmpty()) {
            // Busca pelos IDs das categorias informadas
            Set<Categoria> categorias = new HashSet<>();
            Produto prod = new Produto();
            for (Integer idCat : idsCat) {
                Categoria cat = new Categoria();
                cat.setId(idCat);
                cat.setProdutos(new HashSet<>(Arrays.asList(prod)));
                categorias.add(cat);
            }
            prod.setCategorias(categorias);
            Example<Produto> exProd = Example.of(prod);
            resultados = produtoRepository.findAll(exProd);
        } else {
            // Lista todos os produtos usando paginacao
            resultados = produtoRepository.findAll();
        }
        return new ModelAndView("produtos/lista").addObject("produtos", resultados);
    }
    
    @GetMapping("/teste")
    public ModelAndView listarTeste(
            @RequestParam(name = "param", required = false) String param) {
        List<Produto> resultados = produtoRepository.findByNomeContainingIgnoreCase(param);
        return new ModelAndView("produtos/lista").addObject("produtos", resultados);
    }

    @GetMapping("/novo")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("produtos/form").addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") long id) {

        Optional<Produto> optProd = produtoRepository.findById(id);
        if (!optProd.isPresent()) {
            return new ModelAndView("redirect:/produtos");
        }
        Produto prod = optProd.get();
        if (prod.getCategorias() != null && !prod.getCategorias().isEmpty()) {
            Set<Integer> idsCategorias = new HashSet<>();
            for (Categoria cat : prod.getCategorias()) {
                idsCategorias.add(cat.getId());
            }
            prod.setIdsCategorias(idsCategorias);
        }
        if (prod.getImagens() != null && !prod.getImagens().isEmpty()) {
            prod.setImagensList(new ArrayList<>(prod.getImagens()));
        }
        return new ModelAndView("produtos/form").addObject("produto", prod);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute @Valid Produto produto, BindingResult bindingResult,
            RedirectAttributes redirAttr) {
        produto.setDtCadastro(LocalDateTime.now());
        if (produto.getIdsCategorias() != null && !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Optional<Categoria> optCat = categoriaRepository.findById(idCat);
                if (optCat.isPresent()) {
                    Categoria cat = optCat.get();
                    categoriasSelecionadas.add(cat);
                    cat.setProdutos(new HashSet<>(Arrays.asList(produto)));
                }
            }
            produto.setCategorias(categoriasSelecionadas);
        }
        if (produto.getImagensList() != null && !produto.getImagensList().isEmpty()) {
            Set<ImagemProduto> imagens = new LinkedHashSet<>();
            for (ImagemProduto img : produto.getImagensList()) {
                img.setProduto(produto);
                imagens.add(img);
            }
            produto.setImagens(imagens);
        }
        produtoRepository.save(produto);
        redirAttr.addFlashAttribute("msgSucesso",
                "Produto " + produto.getNome() + " salvo com sucesso");
        return new ModelAndView("redirect:/produtos");
    }

    @PostMapping("/{id}/remover")
    public ModelAndView remover(@PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        produtoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("msgSucesso",
                "Produto ID " + id + " removido com sucesso");
        return new ModelAndView("redirect:/produtos");
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

}
