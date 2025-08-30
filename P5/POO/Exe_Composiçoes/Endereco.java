package Exe_Composiçoes;

public class Endereco {
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int numero, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // Getters
    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    // Setters (if needed for modification)
    // public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    // ...

    @Override
    public String toString() {
        return String.format("%s, %d\n%s, %s - %s\nCEP: %s",
                logradouro, numero, bairro, cidade, estado, cep);
    }
}
