package br.senac.tads.dsw.exemplorest.controller;

import br.senac.tads.dsw.exemplorest.dominio.Interesse;
import br.senac.tads.dsw.exemplorest.dominio.InteresseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/interesses")
// @CrossOrigin(origins = "*")
public class InteresseRestController {
    
    @Autowired
    private InteresseRepository repository;
    
    @GetMapping
    public List<Interesse> findAll() {
        return repository.findAll();
    }
    
}
