package br.senac.tads.dsw.exemplorest.controller;

import br.senac.tads.dsw.exemplorest.dominio.DadosPessoais;
import br.senac.tads.dsw.exemplorest.dominio.DadosPessoaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/rest/pessoas")
// @CrossOrigin(origins = "http://127.0.0.1:8080")
// @CrossOrigin(origins = "*")

// Tags do Springdoc/Swagger: exemplo em https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-spring-boot-2-webmvc/src/main/java/org/springdoc/demo/app2/api/StoreApi.java
@Tag(name = "dados-pessoais", description = "APIs para dados pessoais")
public class DadosPessoaisRestController {
    
    @Autowired
    private DadosPessoaisService service;
    
    @Operation(summary = "Paginação dos dados", description = "Descrição da paginação dos dados", tags = { "dados-pessoais" })
    @GetMapping
    public Page<DadosPessoais> listar(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "qtde", defaultValue = "10") int qtde,
            @RequestParam(name = "interessesIds", required = false) List<Integer> interessesIds) {
        Page<DadosPessoais> resultadosPage = service.findAll(page, qtde, interessesIds);
        return resultadosPage;
    }
    
    @GetMapping("/{id}")
    public DadosPessoais findById(@PathVariable Integer id) {
        Optional<DadosPessoais> optPessoa = service.findById(id);
        if (!optPessoa.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DadosPessoais pessoa = optPessoa.get();
        return pessoa;
    }
    
    @PostMapping
    public ResponseEntity<?> salvarNovo(@RequestBody @Valid DadosPessoais dados,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        dados = service.save(dados);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dados.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> salvarExistente(
            @PathVariable Integer id,
            @RequestBody @Valid DadosPessoais dados,
            BindingResult bindingResult) {
        Optional<DadosPessoais> optPessoa = service.findById(id);
        if (!optPessoa.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        dados.setId(id);
        service.save(dados);
        return ResponseEntity.ok(dados);
    }
    
}
