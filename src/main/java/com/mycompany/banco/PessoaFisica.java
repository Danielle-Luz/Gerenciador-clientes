package com.mycompany.banco;

import java.util.Date;

public class PessoaFisica extends Cliente {
    private String cpf;
    private String nome;
    private Date dataNascimento;

    public PessoaFisica(String cpf, String nome, Date dataNascimento, String numeroConta, int agencia, String telefone, double saldo, double limiteCheque) {
        super(numeroConta, agencia, telefone, saldo, limiteCheque);
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
