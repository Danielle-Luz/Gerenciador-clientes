package com.mycompany.banco;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.mycompany.banco.Cliente;

import javax.management.openmbean.OpenDataException;

public class Gerente {
    private String nome;
    private Cliente[] listaClientes = new Cliente[50];
    
    private String lerValorAlfanumerico (String mensagem) {
        Scanner scan = new Scanner(System.in);

        System.out.print(mensagem);

        String valor = scan.nextLine();

        return valor;
    }

    private double lerValorMonetario (String mensagem) {
        double valor = 0;
        
        do {
            Scanner scan = new Scanner(System.in);

            try {
                System.out.print(mensagem);

                valor = scan.nextDouble();

                break;
            } catch (InputMismatchException ex) {
                System.out.println("Insira um valor monetário válido.");
            }
        } while (true);

        return valor;
    }

    private int lerValorInteiro (String mensagem) {
        
        int valor = 0;
        
        do {
            try {
                Scanner scan = new Scanner(System.in);

                System.out.print(mensagem);

                valor = scan.nextInt();

                break;
            } catch (InputMismatchException ex) {
                System.out.println("Insira um número inteiro válido.");
            }
        } while (true);

        return valor;
    }

    private int lerTipoCliente () {
        int tipoCliente = 0;

        do {
            tipoCliente = lerValorInteiro("Tipo de cliente:\n1 - Físico\n2- Jurídico\n");
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

        String numeroConta = lerValorAlfanumerico("Insira o número da conta do cliente: ");

        int agencia = lerValorInteiro("Insira a agência: ");

        double saldo = lerValorMonetario("Insira o saldo: ");
        
        double limiteCheque = lerValorMonetario("Insira o limite do cheque: ");

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

                return true;
            }
        }

        return false;
    }

    public boolean removerCliente (String numeroConta) {
        for (int i = 0; i < listaClientes.length; i++) {
            if (listaClientes[i] != null) {
                boolean numeroDaContaEhIgual = listaClientes[i].numeroConta.equals(numeroConta);
    
                if (numeroDaContaEhIgual) {
                    listaClientes[i] = null;
    
                    return true;
                }
            }
        }

        return false;
    }

    public void mostrarMenu () {
        Scanner scan = new Scanner(System.in);

        menu:
        do {
            int opcao = 0;
    
            while (true) {
                opcao = lerValorInteiro("Bem vindo,"+ this.nome +".\nEscolha uma opção:\n1- Cadastrar cliente\n2- Remover cliente\n3- Alterar valor do cheque especial\n4- Fazer transferência\n5- Adicionar saldo\n6- Imprimir relatório\n");
    
                if (opcao < 1 || opcao > 5) {
                    System.out.println("Escolha uma opção entre 1 e 6");
    
                    continue;
                }
    
                break;
            }
    
            switch(opcao) {
                case 1: 
                Cliente novoCliente = cadastrarCliente();
    
                boolean clienteFoiInserido = inserirClienteEmLista(novoCliente);
    
                if (clienteFoiInserido) {
                    System.out.println("Cliente inserido com sucesso");
                } else {
                    System.out.println("Lista cheia, não foi possível inserir o cliente");
                }
    
                break;
                case 2:
                String numeroConta = lerValorAlfanumerico("Insira o número da conta do cliente a ser removido: ");
    
                boolean clienteFoiRemovido = removerCliente(numeroConta);
    
                if (clienteFoiRemovido) {
                    System.out.println("O cliente foi removido com sucesso");
                } else {
                    System.out.println("O cliente não foi encontrado");
                }
    
                break;
            }
    
            System.out.println();
           
            do {
                opcao = lerValorInteiro("Realizar nova operação?\n1- Sim\n2- Não\n");
    
                if (opcao == 1) continue menu;
                else if (opcao == 2) break menu;
    
                System.out.println("Selecione 1 ou 2");
            } while (opcao != 1 && opcao != 2);
        } while (true);
    }

    public static void main(String[] args) {
        Gerente gerente = new Gerente();

        gerente.mostrarMenu();
    }
}
