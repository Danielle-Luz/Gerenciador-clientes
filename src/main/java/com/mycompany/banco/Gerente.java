package com.mycompany.banco;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.mycompany.banco.Cliente;


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

    private boolean validaDigito (int inicial, int digitoVerificador, int[]digitosNumericos) {
        int soma = 0;

        for (int fator = inicial, i = 0; fator <= 2; fator--, i++) {
            soma += digitosNumericos[i] * fator;
        }

        double valorComparado = 0;

        double restoDivisao = (soma * 10) % 11;

        if (restoDivisao != 10 && restoDivisao != 11) {
            valorComparado = restoDivisao;
        }

        if (valorComparado == digitoVerificador) {
            return true;
        }

        return false;
    }

    private int[] obterArrayDeNumeros (String[] digitosString) {
        int[] digitosNumericos = new int[digitosString.length];

        for (int i = 0; i < digitosString.length; i++) {
            digitosNumericos[i] = Integer.parseInt(digitosString[i]);
        }

        return digitosNumericos;
    }

    public boolean validaAutenticidadeCpf (String cpf) {
        String[] digitosString = cpf.substring(0, 9).split("");

        int digitoVerificadorUm = Integer.parseInt(cpf.split("")[9]);
        int digitoVerificadorDois = Integer.parseInt(cpf.split("")[10]);

        int[] digitosNumericos = obterArrayDeNumeros(digitosString);

        boolean primeiroDigitoEhValido = validaDigito(10, digitoVerificadorUm, digitosNumericos);
        boolean segundoDigitoEhValido = validaDigito(11, digitoVerificadorDois, digitosNumericos);

        boolean cpfEhValido = primeiroDigitoEhValido && segundoDigitoEhValido;
        
        return cpfEhValido;
    }

    public String validaDigitosDoCpf () {
        while (true) {
            String cpf = lerValorAlfanumerico("Insira o CPF: ");

            if (cpf.matches("[a-zA-Z]")) {
                System.out.println("O CPF deve possuir apenas números");

                continue;
            }

            if (cpf.length() != 11) {
                System.out.println("O CPF deve ter 11 dígitos");

                continue;
            }

            return cpf;
        }
    }

    public String validaCpf () {
        while (true) {
            String cpf = validaDigitosDoCpf();

            boolean cpfEhValido = validaAutenticidadeCpf(cpf);

            if (!cpfEhValido) {
                System.out.println("Digite um CPF verdadeiro");

                continue;
            }

            return cpf;
        }

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

            String cpf = validaCpf();
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

    public Cliente consultarCliente () {
        String numeroConta = lerValorAlfanumerico("Insira o número da conta do cliente procurado: ");

        for (int i = 0; i < listaClientes.length; i++) {
            
            if (listaClientes[i] != null) {
                boolean numeroDaContaEhIgual = listaClientes[i].numeroConta.equals(numeroConta);
    
                if (numeroDaContaEhIgual) {
                    return listaClientes[i];
                }
            }
        }

        return null;
    }

    public void exibirCliente () {
        Cliente clienteEncontrado = consultarCliente();

        if (clienteEncontrado == null) {
            System.out.println("O cliente procurado não foi encontrado");
        } else {
            System.out.println(clienteEncontrado.toString());
        }
    } 

    public void alterarLimiteDoChequeEspecial () {
        int opcao;

        Cliente clienteEncontrado = consultarCliente();

        double valor = lerValorMonetario("Valor a ser acrescido ou removido do cheque especial: ");

        do {
            opcao = lerValorInteiro("1- Aumentar limite\n2- Diminuir limite\n");
        } while(opcao != 1 && opcao != 2);
        
        if (opcao == 1) {
            clienteEncontrado.limiteCheque += valor;

            System.out.println("Valor acrescido no cheque especial");
        } else {
            clienteEncontrado.limiteCheque -= valor;
            
            System.out.println("Valor removido do cheque especial");
        }
    }

    public boolean transferirValor (Cliente transferidor, Cliente receptor, double valorTransferido) {
        if (transferidor.saldo >= valorTransferido) {
            transferidor.saldo -= valorTransferido;

            receptor.saldo += valorTransferido;

            return true;
        }

        return false;
    }

    public void fazerTransferencia () {
        Cliente transferidor;
        Cliente receptor;

        do {
            System.out.println("Insira os dados do cliente a transferir o valor.");

            transferidor = consultarCliente();
        } while (transferidor == null);

        do {
            System.out.println("Insira os dados do cliente a receber o valor.");

            receptor = consultarCliente();            
        } while (receptor == null);

        double valorTransferido = lerValorMonetario("Valor a ser transferido: ");
        
        boolean valorFoiTransferido = transferirValor(transferidor, receptor, valorTransferido);

        if (valorFoiTransferido) {
            System.out.println("Valor transferido com sucesso");
        } else {
            System.out.println("Não foi possível transferir o valor");
        }
    }

    public void adicionarSaldo () {
        Cliente receptor = consultarCliente();

        if (receptor != null) {
            double valorTransferido = lerValorMonetario("Valor a ser transferido: ");

            receptor.saldo += valorTransferido;

            System.out.println("Valor adicionado ao saldo do cliente");
        } else {
            System.out.println("Não foi possível inserir o valor pois o cliente não foi encontrado");
        }
    }

    public void exibirClientes () {
        int quantidadeNulos = 0;

        for (Cliente cliente : listaClientes) {
            if (cliente != null) {
                System.out.println("-----------------------");
                System.out.println(cliente);
                System.out.println("-----------------------");
            } else {
                quantidadeNulos++;
            }
        }

        if (quantidadeNulos == listaClientes.length) {
            System.out.println("Não há nenhum cliente inserido na lista");
        }
    }

    public void mostrarMenu () {
        Scanner scan = new Scanner(System.in);

        menu:
        do {
            int opcao = 0;
    
            while (true) {
                opcao = lerValorInteiro("Bem vindo,"+ this.nome +".\nEscolha uma opção:\n1- Cadastrar cliente\n2- Remover cliente\n3- Alterar valor do cheque especial\n4- Fazer transferência\n5- Adicionar saldo\n6- Imprimir relatório\n7- Consultar cliente\n");
    
                if (opcao < 1 || opcao > 7) {
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

                case 3:
                alterarLimiteDoChequeEspecial();

                break;

                case 4:
                fazerTransferencia();

                break;

                case 5:
                adicionarSaldo();

                break;

                case 6:
                exibirClientes();

                break;

                case 7:
                exibirCliente();

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
