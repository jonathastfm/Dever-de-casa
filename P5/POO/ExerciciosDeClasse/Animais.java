package ExerciciosDeClasse;

// Exercicio 2 - a
class Animal { // [cite: 4]
    private String nome;
    private String raca;

    public Animal() { // [cite: 4]
        this.nome = "Desconhecido";
        this.raca = "Desconhecida";
    }

    public Animal(String nome) { // [cite: 4]
        this.nome = nome;
        this.raca = "Desconhecida";
    }

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String caminha() { // [cite: 4]
        return nome + " está caminhando.";
    }
}

class Cachorro extends Animal { // [cite: 4]
    public Cachorro() {
        super();
    }

    public Cachorro(String nome) {
        super(nome);
    }

    public Cachorro(String nome, String raca) {
        super(nome, raca);
    }

    public String late() { // [cite: 4]
        return getNome() + " está latindo: Au au!";
    }
}

class Gato extends Animal { // [cite: 4]
    public Gato() {
        super();
    }

    public Gato(String nome) {
        super(nome);
    }

    public Gato(String nome, String raca) {
        super(nome, raca);
    }

    public String mia() { // [cite: 4]
        return getNome() + " está miando: Miau!";
    }
}

// Exercicio 2 - b
class Pessoa { // [cite: 5]
    private String nome;
    private int idade;

    public Pessoa() { // [cite: 5]
        this.nome = "Não informado";
        this.idade = 0;
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void exibeDadosPessoa() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

class Rica extends Pessoa { // [cite: 5]
    private double dinheiro;

    public Rica(String nome, int idade, double dinheiro) {
        super(nome, idade);
        this.dinheiro = dinheiro;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void fazCompras() { // [cite: 5]
        System.out.println(getNome() + " está fazendo compras com seu dinheiro: R$" + String.format("%.2f", dinheiro));
    }
}

class Pobre extends Pessoa { // [cite: 5]
    public Pobre(String nome, int idade) {
        super(nome, idade);
    }

    public void trabalha() { // [cite: 5]
        System.out.println(getNome() + " está trabalhando.");
    }
}

class Miseravel extends Pessoa { // [cite: 5]
    public Miseravel(String nome, int idade) {
        super(nome, idade);
    }

    public void mendiga() { // [cite: 5]
        System.out.println(getNome() + " está mendigando.");
    }
}
