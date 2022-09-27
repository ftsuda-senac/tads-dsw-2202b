package br.senac.tads.dsw.exemplos.springmvc;

import java.time.LocalDateTime;

public class Dados {
    
    private String titulo;
    
    private int numero;
    
    private LocalDateTime dataHoraAtual;
    
    private String codigo;
    
    public Dados() {
        
    }
    
    public Dados(String titulo, int numero, LocalDateTime dataHoraAtual) {
        this.titulo = titulo;
        this.numero = numero;
        this.dataHoraAtual = dataHoraAtual;
    }
    
    public Dados(String titulo, int numero, LocalDateTime dataHoraAtual, String codigo) {
        this.titulo = titulo;
        this.numero = numero;
        this.dataHoraAtual = dataHoraAtual;
        this.codigo = codigo;
    }

    public String getTitulo() {
        return "Pegadinha do Malandro";
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public LocalDateTime getDataHoraAtual() {
        return dataHoraAtual;
    }

    public void setDataHoraAtual(LocalDateTime dataHoraAtual) {
        this.dataHoraAtual = dataHoraAtual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
