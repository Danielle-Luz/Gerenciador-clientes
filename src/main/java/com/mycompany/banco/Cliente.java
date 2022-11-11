package com.mycompany.banco;


public abstract class Cliente {
    String numeroConta;
    int agencia;
    String telefone;
    double saldo;
    double limiteCheque;

    public Cliente (String numeroConta, int agencia, String telefone, double saldo, double limiteCheque) {
       this.agencia = agencia;
       this.numeroConta = numeroConta;
       this.telefone = telefone;
       this.saldo = saldo;
       this.limiteCheque = limiteCheque;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteCheque() {
        return limiteCheque;
    }
}
