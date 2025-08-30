package Exe_Composiçoes;

public class Pessoa {
    private String nome;
    private Data dataNasc;      // Composition: Pessoa has a Data
    private Endereco endereco;  // Composition: Pessoa has an Endereco
    private Contato contato;    // Composition: Pessoa has a Contato

    public Pessoa(String nome, Data dataNasc, Endereco endereco, Contato contato) {
        this.nome = nome;
        // The objects of Data, Endereco, and Contato are passed into the Pessoa constructor.
        // This demonstrates composition, where Pessoa owns these objects.
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.contato = contato;
    }

    // Getters for Pessoa's attributes
    public String getNome() {
        return nome;
    }

    public Data getDataNasc() {
        return dataNasc;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Contato getContato() {
        return contato;
    }

    // Setters (to allow changing the composed objects after creation)
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(Data dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }


    @Override
    public String toString() {
        return String.format("Nome: %s\nData de Nascimento: %s\nEndereço:\n%s\nContato:\n%s",
                nome, dataNasc.toString(), endereco.toString(), contato.toString());
    }
}