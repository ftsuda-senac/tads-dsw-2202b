package br.senac.tads.dsw.exemplospringsecurity.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import br.senac.tads.dsw.exemplospringsecurity.dominio.Papel;
import br.senac.tads.dsw.exemplospringsecurity.dominio.UsuarioSistema;

@Service
public class UsuarioSistemaService {

    private final Map<String, UsuarioSistema> usuariosCadastrados = new LinkedHashMap<>();
    
//    private PasswordEncoder passwordEncoder;
//    
//    public UsuarioSistemaService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    private String gerarHashSenha(String senhaAberta) {
        // return passwordEncoder.encode(senhaAberta);

        // ***** VERSÃO INICIAL: RETORNANDO SENHA ABERTA - NÃO USAR EM PROJETOS REAIS
        return senhaAberta;
    }

    @PostConstruct
    private void init() {
        usuariosCadastrados.put("denver", new UsuarioSistema("denver", "Ricardo Ramos",
                gerarHashSenha("abcd1234"), Arrays.asList(new Papel("ROLE_PEAO"))));
        usuariosCadastrados.put("tokio", new UsuarioSistema("tokio", "Silene Oliveira",
                gerarHashSenha("abcd1234"), Arrays.asList(new Papel("ROLE_PEAO"))));
        usuariosCadastrados.put("berlin",
                new UsuarioSistema("berlin", "Andres de Fonollosa", gerarHashSenha("abcd1234"),
                        Arrays.asList(new Papel("ROLE_PEAO"), new Papel("ROLE_FODON"))));
        usuariosCadastrados.put("professor",
                new UsuarioSistema("professor", "Sergio Marquina", gerarHashSenha("abcd1234"),
                        Arrays.asList(new Papel("ROLE_PEAO"), new Papel("ROLE_GOD"))));
    }

}
