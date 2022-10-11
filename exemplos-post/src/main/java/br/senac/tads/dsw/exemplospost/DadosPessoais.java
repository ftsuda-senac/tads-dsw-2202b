package br.senac.tads.dsw.exemplospost;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class DadosPessoais {

    @Size(max = 50)
    @NotBlank(message = "Preencha seu nome seu animal")
    private String nome;

    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // ISO-8601
    private LocalDate dataNascimento;

    @Email
    @Size(max = 50)
    @NotBlank
    private String email;

    @Size(max = 16)
    private String telefone;

    @NotBlank
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
