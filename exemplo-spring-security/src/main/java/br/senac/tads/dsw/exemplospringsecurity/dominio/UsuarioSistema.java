package br.senac.tads.dsw.exemplospringsecurity.dominio;

import java.util.List;

/**
 * Após implementar UserDetais:
 *
 * - isAccountNonExpired() -> Verificar se o cadastro do usuario está expirado
 * (Ex: data de validade da conta)
 *
 * - isAccountNonLocked() -> Verificar se usuário está bloqueado (Ex: quantidade
 * de logins errados excedida)
 *
 * - isCredentialsNonExpired() -> Verificar se a senha está expirada (Ex: X dias
 * após ultima atualização da senha)
 *
 */
public class UsuarioSistema {

    private String username;

    private String nomeCompleto;

    private String hashSenha;

    private List<Papel> papeis;

    public UsuarioSistema() {
    }

    public UsuarioSistema(String username, String nomeCompleto, String hashSenha,
            List<Papel> papeis) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.hashSenha = hashSenha;
        this.papeis = papeis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

}
