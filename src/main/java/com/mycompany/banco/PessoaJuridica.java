package com.mycompany.banco;


public class PessoaJuridica extends Cliente{
    private String cnpj;
    private String[] socios;
    private String razaoSocial;
    private String nomeFantasia;

    public PessoaJuridica(String cnpj, String[] socios, String razaoSocial, String nomeFantasia, String numeroConta, int agencia, String telefone, double saldo, double limiteCheque) {
        super(numeroConta, agencia, telefone, saldo, limiteCheque);
        this.cnpj = cnpj;
        this.socios = socios;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public boolean setSocios(String novoSocio, String antigoSocio) {
        for (int i = 0; i < socios.length; i++) {
            if (socios[i].equalsIgnoreCase(antigoSocio)) {
                socios[i] = novoSocio;
                
                System.out.println("Sócio substituído com sucesso");
                
                return true;
            }
        }
        
        System.out.println("Antigo sócio não encontrado");
        
        return false;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getSocios() {
        String sociosNomes = "";
        for(String socio : socios) {
            sociosNomes += socio + ", ";
        }

        return sociosNomes.substring(0, sociosNomes.length() - 1);
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }
    
    @Override
    public String toString () {
        String dados = "";

        dados += String.format("CNPJ: %s\n", this.cnpj);
        dados += String.format("Razão social: %s\n", this.razaoSocial);
        dados += String.format("Nome fantasia: %s\n", this.nomeFantasia);
        dados += String.format("Sócios: %s\n", this.getSocios());
        dados += String.format("Telefone: %s\n", this.telefone);
        dados += String.format("Número da conta: %s\n", this.numeroConta);
        dados += String.format("Agência: %d\n", this.agencia);
        dados += String.format("Saldo: %.2f\n", this.saldo);
        dados += String.format("Limite de cheque: %.2f\n", this.limiteCheque);

        return dados;
    }
}