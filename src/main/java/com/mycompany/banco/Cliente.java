package com.mycompany.banco;


public abstract class Cliente {
    private String numeroConta;
    private int agencia;
    private String telefone;
    private double saldo;
    private double limiteCheque;

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

    public void aumentarSaldo (double valor) {
        this.saldo += valor;
    }

    public void diminuirSaldo (double valor) {
        this.saldo -= valor;
    }

    public void aumentarCheque (double valor) {
        this.limiteCheque += valor;
    }

    public void diminuirCheque (double valor) {
        this.limiteCheque -= valor;
    }
 }
