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
}
