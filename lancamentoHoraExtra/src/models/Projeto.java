package models;

import java.util.Date;

public class Projeto {

    private String nome;
    private Cliente cliente;
    private Integer verba;
    private Date prazoDeExecucao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getVerba() {
        return verba;
    }

    public void setVerba(Integer verba) {
        this.verba = verba;
    }

    public Date getPrazoDeExecucao() {
        return prazoDeExecucao;
    }

    public void setPrazoDeExecucao(Date prazoDeExecucao) {
        this.prazoDeExecucao = prazoDeExecucao;
    }
}
