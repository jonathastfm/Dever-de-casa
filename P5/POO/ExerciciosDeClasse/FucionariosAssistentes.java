// Exercicio 1
package ExerciciosDeClasse;

class Funcionario {
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void addAumento(double valor) { // [cite: 1]
        this.salario += valor;
    }

    public double ganhoAnual() { // [cite: 1]
        return this.salario * 12;
    }

    public void exibeDados() { // [cite: 1]
        System.out.println("Nome: " + this.nome);
        System.out.println("Salário: R$" + String.format("%.2f", this.salario));
        System.out.println("Ganho Anual: R$" + String.format("%.2f", this.ganhoAnual()));
    }
}

class Assistente extends Funcionario { // [cite: 2]
    private int numeroMatricula;

    public Assistente(String nome, double salario, int numeroMatricula) {
        super(nome, salario);
        this.numeroMatricula = numeroMatricula;
    }

    public int getNumeroMatricula() { // [cite: 2]
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) { // [cite: 2]
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public void exibeDados() { // [cite: 3]
        super.exibeDados();
        System.out.println("Número de Matrícula: " + this.numeroMatricula);
    }
}

class Tecnico extends Assistente {
    private double bonusSalarial;

    public Tecnico(String nome, double salario, int numeroMatricula, double bonusSalarial) {
        super(nome, salario, numeroMatricula);
        this.bonusSalarial = bonusSalarial;
    }

    public double getBonusSalarial() {
        return bonusSalarial;
    }

    public void setBonusSalarial(double bonusSalarial) {
        this.bonusSalarial = bonusSalarial;
    }

    @Override
    public double ganhoAnual() {
        return (super.getSalario() + this.bonusSalarial) * 12;
    }

    @Override
    public void exibeDados() {
        super.exibeDados();
        System.out.println("Bônus Salarial: R$" + String.format("%.2f", this.bonusSalarial));
        System.out.println("Ganho Anual (com bônus): R$" + String.format("%.2f", this.ganhoAnual()));
    }
}

class Administrativo extends Assistente {
    private String turno; // "dia" ou "noite"
    private double adicionalNoturno;

    public Administrativo(String nome, double salario, int numeroMatricula, String turno, double adicionalNoturno) {
        super(nome, salario, numeroMatricula);
        this.turno = turno;
        if (turno.equalsIgnoreCase("noite")) {
            this.adicionalNoturno = adicionalNoturno;
        } else {
            this.adicionalNoturno = 0; // Sem adicional para turno do dia
        }
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno, double novoAdicionalNoturno) {
        this.turno = turno;
        if (this.turno.equalsIgnoreCase("noite")) {
            this.adicionalNoturno = novoAdicionalNoturno;
        } else {
            this.adicionalNoturno = 0;
        }
    }

    public double getAdicionalNoturno() {
        return adicionalNoturno;
    }

    @Override
    public double ganhoAnual() {
        if (turno.equalsIgnoreCase("noite")) {
            return (super.getSalario() + this.adicionalNoturno) * 12;
        }
        return super.ganhoAnual();
    }

    @Override
    public void exibeDados() {
        super.exibeDados();
        System.out.println("Turno: " + this.turno);
        if (turno.equalsIgnoreCase("noite")) {
            System.out.println("Adicional Noturno: R$" + String.format("%.2f", this.adicionalNoturno));
        }
        System.out.println("Ganho Anual (com adicional, se aplicável): R$" + String.format("%.2f", this.ganhoAnual()));
    }
}