package ExerciciosDeClasse;

// Exercicio 4
class Imovel {
    private String endereco;
    private double preco;

    public Imovel(String endereco, double preco) {
        this.endereco = endereco;
        this.preco = preco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void imprimePrecoBase() {
        System.out.println("Endereço: " + endereco);
        System.out.println("Preço Base: R$" + String.format("%.2f", preco));
    }
}

class Novo extends Imovel { // [cite: 12]
    private double adicionalNoPreco;

    public Novo(String endereco, double preco, double adicionalNoPreco) {
        super(endereco, preco);
        this.adicionalNoPreco = adicionalNoPreco;
    }

    public double getAdicionalNoPreco() { // [cite: 13]
        return adicionalNoPreco;
    }

    public void setAdicionalNoPreco(double adicionalNoPreco) { // [cite: 13]
        this.adicionalNoPreco = adicionalNoPreco;
    }

    public double getPrecoFinalNovo() {
        return super.getPreco() + this.adicionalNoPreco;
    }

    public void imprimeValorAdicional() { // [cite: 13]
        System.out.println("Adicional de Imóvel Novo: R$" + String.format("%.2f", this.adicionalNoPreco));
    }

    public void imprimePrecoFinal() {
        super.imprimePrecoBase();
        imprimeValorAdicional();
        System.out.println("Preço Final (Novo): R$" + String.format("%.2f", getPrecoFinalNovo()));
    }
}

class Velho extends Imovel { // [cite: 14]
    private double descontoNoPreco;

    public Velho(String endereco, double preco, double descontoNoPreco) {
        super(endereco, preco);
        this.descontoNoPreco = descontoNoPreco;
    }

    public double getDescontoNoPreco() { // [cite: 15]
        return descontoNoPreco;
    }

    public void setDescontoNoPreco(double descontoNoPreco) { // [cite: 15]
        this.descontoNoPreco = descontoNoPreco;
    }

    public double getPrecoFinalVelho() {
        return super.getPreco() - this.descontoNoPreco;
    }

    public void imprimeDesconto() { // [cite: 15]
        System.out.println("Desconto de Imóvel Velho: R$" + String.format("%.2f", this.descontoNoPreco));
    }

    public void imprimePrecoFinal() {
        super.imprimePrecoBase();
        imprimeDesconto();
        System.out.println("Preço Final (Velho): R$" + String.format("%.2f", getPrecoFinalVelho()));
    }
}