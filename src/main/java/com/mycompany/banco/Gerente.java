package com.mycompany.banco;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerente {
    private String nome;
    private Cliente[] listaClientes = new Cliente[50];

    Scanner scan = new Scanner(System.in);
    
    private String lerValorAlfanumerico (String mensagem) {
        System.out.print(mensagem);

        String valor = scan.nextLine();

        return valor;
    }

    private double lerValorMonetario (String mensagem) {
        double valor = 0;

        do {
            try {
                System.out.print(mensagem);

                valor = scan.nextDouble();

                break;
            } catch (InputMismatchException ex) {
                System.out.print("Insira um valor monetário válido.");
            }
        } while (true);

        return valor;
    }

    private int lerValorInteiro (String mensagem) {
        int valor = 0;

        do {
            try {
                System.out.print(mensagem);

                valor = scan.nextInt();

                break;
            } catch (InputMismatchException ex) {
                System.out.print("Insira um número inteiro válido.");
            }
        } while (true);

        return valor;
    }

    private int lerTipoCliente () {
        int tipoCliente = 0;

        do {
            tipoCliente = lerValorInteiro("Tipo de cliente: 1 - Físico\n2- Jurídico");
        } while (tipoCliente != 1 && tipoCliente != 2);

        return tipoCliente;
    }

    private String[] lerSocios () {
        String[] socios = new String[3];

        for (int i = 0; i < socios.length; i++) {
            socios[i] = lerValorAlfanumerico("Insira o nome do sócio: ");
        }

        return socios;
    }

    public Cliente cadastrarCliente () {
        Cliente novoCliente;

        String numeroConta = lerValorAlfanumerico("Insira o nome do cliente: ");

        int agencia = lerValorInteiro("Insira a agência: ");

        double saldo = lerValorMonetario("Insira o saldo: ");
        
        double limiteCheque = lerValorMonetario("Insira o limite do cheque: ");
        
        scan.nextLine();

        String telefone = lerValorAlfanumerico("Insira o telefone: ");

        int tipoCliente = lerTipoCliente();

        if (tipoCliente == 1) {
            String cpf = lerValorAlfanumerico("Insira o CPF: ");
            String nome = lerValorAlfanumerico("Insira o nome do cliente: ");
            String dataNascimento = lerValorAlfanumerico("Insira a data de nascimento do cliente: ");

            novoCliente = new PessoaFisica(cpf, nome, dataNascimento, numeroConta, agencia, telefone, saldo, limiteCheque);

        } else {
            String cnpj = lerValorAlfanumerico("Insira o CNPJ do cliente: ");
            String[] socios = lerSocios();
            String razaoSocial = lerValorAlfanumerico("Insira a razão social: ");
            String nomeFantasia = lerValorAlfanumerico("Insira o nome fantasia: ");

            novoCliente = new PessoaJuridica(cnpj, socios, razaoSocial, nomeFantasia, numeroConta, agencia, telefone, saldo, limiteCheque);
        }

        return novoCliente;
    }

    private boolean inserirClienteEmLista (Cliente novoCliente) {
        for(int i = 0; i < listaClientes.length; i++) {
            if (listaClientes[i] == null) {
                listaClientes[i] = novoCliente;

                System.out.println("Cliente inserido com sucesso");
                return true;
            }
        }

        System.out.println("Lista cheia, não foi possível inserir o cliente");
        return false;
    }

    public boolean removerCliente (String numeroConta) {
        for (int i = 0; i < listaClientes.length; i++) {
            boolean numeroDaContaEhIgual = listaClientes[i].numeroConta.equals(numeroConta);

            if (numeroDaContaEhIgual) {
                listaClientes[i] = null;

                return true;
            }
        }

        return false;
    }


}
