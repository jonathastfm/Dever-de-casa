import java.text.NumberFormat;
import java.util.Locale;

// Removed package declaration


public class Funcionario {

    // --- Atributos de Entrada ---
    private String matricula;
    private String nome;
    private int numeroDependentes;
    private double salarioBase;
    private int producaoItens;

    // --- Atributos Calculados ---
    private double gratificacao;
    private double salarioBruto;
    private double descontoINSS;
    private double descontoDependentesIRPF;
    private double descontoIRPF;
    private double salarioLiquido;

    // --- Formatação de Moeda ---
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    // --- Construtor ---
    public Funcionario(String matricula, String nome, int numeroDependentes, double salarioBase, int producaoItens) {
        this.matricula = matricula;
        this.nome = nome;
        this.numeroDependentes = numeroDependentes;
        this.salarioBase = salarioBase;
        this.producaoItens = producaoItens;

        // Realiza os cálculos ao criar o objeto
        calcularContrachequeCompleto();
    }

    // --- Métodos de Cálculo Principais ---

    /**
     * Orquestra todos os cálculos do contracheque.
     */
    private void calcularContrachequeCompleto() {
        this.gratificacao = calcularGratificacao();
        this.salarioBruto = calcularSalarioBruto();
        // Assume a existência das classes TabelaINSS e TabelaIRPF
        // Se não existirem, a lógica de cálculo precisa ser implementada aqui ou nessas classes.
        this.descontoINSS = TabelaINSS.calcular(this.salarioBruto); // [cite: 4]
        this.descontoDependentesIRPF = TabelaIRPF.calcularDescontoDependentes(this.numeroDependentes); // [cite: 5]
        double baseCalculoIRPF = this.salarioBruto - this.descontoINSS - this.descontoDependentesIRPF;
        this.descontoIRPF = TabelaIRPF.calcular(baseCalculoIRPF); // [cite: 4]
        this.salarioLiquido = calcularSalarioLiquido(); // [cite: 2]
    }

    /**
     * Calcula a gratificação com base na produção.
     * Refere-se à "Tabela de Gratificação". [cite: 15]
     * @return O valor da gratificação.
     */
    private double calcularGratificacao() {
        if (this.producaoItens > 2000) {
            return 2250.00;
        } else if (this.producaoItens > 1000) {
            return 1250.00;
        } else {
            // Inclui o caso de exatamente 1000 itens ou menos
            return 500.00;
        }
    }

    /**
     * Calcula o salário bruto.
     * Salário Bruto = Salário Base + Gratificação. [cite: 3]
     * @return O valor do salário bruto.
     */
    private double calcularSalarioBruto() {
        return this.salarioBase + this.gratificacao;
    }

    /**
     * Calcula o salário líquido.
     * Salário Líquido = Salário Bruto - Desconto INSS - Desconto IRPF. [cite: 2, 4]
     * @return O valor do salário líquido.
     */
    private double calcularSalarioLiquido() {
        return this.salarioBruto - this.descontoINSS - this.descontoIRPF;
    }

    // --- Método para Gerar Contracheque ---

    /**
     * Formata e retorna os dados do contracheque como String. [cite: 9, 11]
     * @return String formatada do contracheque.
     */
    public String gerarContracheque() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------\n");
        sb.append("           CONTRACHEQUE                 \n");
        sb.append("----------------------------------------\n");
        sb.append("Matrícula: ").append(matricula).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Dependentes: ").append(numeroDependentes).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("Salário Base: ").append(currencyFormat.format(salarioBase)).append("\n");
        sb.append("Gratificação Produção: ").append(currencyFormat.format(gratificacao)).append("\n"); // [cite: 10]
        sb.append("Salário Bruto: ").append(currencyFormat.format(salarioBruto)).append("\n"); // [cite: 10]
        sb.append("----------------------------------------\n");
        sb.append("(-) Desconto INSS: ").append(currencyFormat.format(descontoINSS)).append("\n"); // [cite: 10]
        sb.append("(-) Desconto IRPF: ").append(currencyFormat.format(descontoIRPF)).append("\n"); // [cite: 10]
        sb.append("    (Abatimento Dependentes: ").append(currencyFormat.format(descontoDependentesIRPF)).append(")\n"); // [cite: 10] Alterado para clareza
        sb.append("----------------------------------------\n");
        sb.append("Salário Líquido: ").append(currencyFormat.format(salarioLiquido)).append("\n"); // [cite: 11]
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    // --- Setters (para modificar os dados, se necessário) ---

    public void setMatricula(String matricula) {
        this.matricula = matricula;
        calcularContrachequeCompleto();
    }

    public void setNome(String nome) {
        this.nome = nome;
        calcularContrachequeCompleto();
    }

    public void setNumeroDependentes(int numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
        calcularContrachequeCompleto();
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
        calcularContrachequeCompleto();
    }

    public void setProducaoItens(int producaoItens) {
        this.producaoItens = producaoItens;
        calcularContrachequeCompleto();
    }

    // --- Getters (para acesso aos dados, se necessário) ---

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public int getProducaoItens() {
        return producaoItens;
    }

    public double getGratificacao() {
        return gratificacao;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getDescontoINSS() {
        return descontoINSS;
    }

    public double getDescontoDependentesIRPF() {
        return descontoDependentesIRPF;
    }

    public double getDescontoIRPF() {
        return descontoIRPF;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    
    
    

    
    
}