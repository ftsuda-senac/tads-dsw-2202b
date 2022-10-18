/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplosspring.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ftsuda
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping
    public ModelAndView mostrarTela() {
        return new ModelAndView("upload");
    }

    @PostMapping
    public ModelAndView receberArquivo(
            @RequestParam("arquivo") MultipartFile[] arquivos,
            RedirectAttributes redirAttr) {

        for (MultipartFile arquivo : arquivos) {

            if (arquivo.isEmpty()) {
                // Arquivo vazio - apresenta erro
                redirAttr.addFlashAttribute("msg", "Erro - Arquivo inválido");
            }
            // Copiar arquivo para diretório destino
            Path destino = Paths.get("C:/senac/servidor-upload/"
                    + arquivo.getOriginalFilename());
            try {
                Files.write(destino, arquivo.getBytes());
                redirAttr.addFlashAttribute("msg", "Arquivo carregado com sucesso");

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return new ModelAndView("redirect:/upload");
    }
    
    @GetMapping("/ver-imagens")
    public ModelAndView mostrarImagensUpload() {
        
        // Busca imagens carregadas no diretório de upload
        File diretorio = new File("C:/senac/servidor-upload/");
        File[] listaArquivos = diretorio.listFiles();
        
        List<String> arquivosImagem = new ArrayList<>();
        for (File arquivo : listaArquivos) {
            if (arquivo.getName().endsWith("jpg") || arquivo.getName().endsWith("png")) {
                arquivosImagem.add(arquivo.getName());
            }
        }
        
        ModelAndView mv = new ModelAndView("ver-imagens");
        mv.addObject("arquivos", arquivosImagem);
        return mv;   
    }

}
