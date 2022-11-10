package com.mycompany.banco;

public class PessoaFisica extends Cliente {
    private String cpf;
    private String nome;
    private String dataNascimento;

    public PessoaFisica(String cpf, String nome, String dataNascimento, String numeroConta, int agencia, String telefone, double saldo, double limiteCheque) {
        super(numeroConta, agencia, telefone, saldo, limiteCheque);
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public String getCpf () {
        return this.cpf;
    }
    
    @Override
    public String toString () {
        String dados = "";

        dados += String.format("Nome: %s\n", this.nome);
        dados += String.format("Nome: %s\n", this.cpf);
        dados += String.format("Data de nascimento: %s\n", this.dataNascimento);
        dados += String.format("Número da conta: %s\n", this.numeroConta);
        dados += String.format("Agência: %d\n", this.agencia);
        dados += String.format("Telefone: %s\n", this.telefone);
        dados += String.format("Saldo: %.2f\n", this.saldo);
        dados += String.format("Limite de cheque: %.2f\n", this.limiteCheque);

        return dados;
    }
}
