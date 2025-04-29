import java.text.NumberFormat;
import java.util.Locale;

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
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

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

    // --- Classes Placeholder para TabelaINSS e TabelaIRPF ---
    // --- Substitua pela implementação real ou importe-as ---

    static class TabelaINSS {
        /**
         * Calcula o desconto do INSS com base na tabela. [cite: 13]
         * (Implementação simplificada/placeholder - precisa detalhar as faixas)
         */
        public static double calcular(double salarioBruto) {
            // Implementação real com base na tabela [cite: 13]
            if (salarioBruto <= 1412.00) {
                return salarioBruto * 0.075;
            } else if (salarioBruto <= 2666.68) {
                // Cálculo progressivo (exemplo simplificado, o cálculo real é mais complexo)
                 return (1412.00 * 0.075) + ((salarioBruto - 1412.00) * 0.09);
                //return salarioBruto * 0.09; // Simples para placeholder
            } else if (salarioBruto <= 4000.03) {
                 return (1412.00 * 0.075) + ((2666.68 - 1412.00) * 0.09) + ((salarioBruto - 2666.68)*0.12);
                //return salarioBruto * 0.12; // Simples para placeholder
            } else { // Acima de 4000.03
                 return (1412.00 * 0.075) + ((2666.68 - 1412.00) * 0.09) + ((4000.03 - 2666.68)*0.12) + ((salarioBruto-4000.03)*0.14);
                //return salarioBruto * 0.14; // Simples para placeholder
            }
             // Nota: O cálculo real do INSS é progressivo por faixa.
             // Esta é uma simplificação para o exemplo. A implementação correta
             // deve calcular o valor devido em cada faixa e somar.
        }
    }

    static class TabelaIRPF {
        private static final double VALOR_POR_DEPENDENTE = 123.00; // [cite: 5]

        /**
         * Calcula o valor total da dedução por dependentes. [cite: 5]
         */
        public static double calcularDescontoDependentes(int numeroDependentes) {
            return numeroDependentes * VALOR_POR_DEPENDENTE;
        }

        /**
         * Calcula o IRPF com base na base de cálculo (Salario Bruto - INSS - Dependentes) e tabela. [cite: 14]
         * (Implementação simplificada/placeholder - precisa detalhar as faixas e deduções)
         */
        public static double calcular(double baseCalculo) {
            double imposto = 0;
            double parcelaDeduzir = 0;

            if (baseCalculo <= 2259.20) { // Isento [cite: 14]
                imposto = 0;
                parcelaDeduzir = 0;
            } else if (baseCalculo <= 2826.65) { // 7.5% [cite: 14]
                imposto = baseCalculo * 0.075;
                parcelaDeduzir = 169.44; // Valor da dedução para esta faixa (necessário consultar tabela completa)
            } else if (baseCalculo <= 3751.05) { // 15% [cite: 14]
                imposto = baseCalculo * 0.15;
                parcelaDeduzir = 381.44; // Valor da dedução para esta faixa
            } else if (baseCalculo <= 4664.68) { // 22.5% [cite: 14]
                imposto = baseCalculo * 0.225;
                 parcelaDeduzir = 662.77; // Valor da dedução para esta faixa
            } else { // Acima de 4664.68 - 27.5% [cite: 14]
                imposto = baseCalculo * 0.275;
                parcelaDeduzir = 896.00; // Valor da dedução para esta faixa
            }

             // O cálculo do IRPF geralmente envolve aplicar a alíquota e subtrair uma parcela a deduzir.
             // return (baseCalculo * aliquota) - parcelaADeduzir;
             // A implementação acima está simplificada. É preciso consultar a tabela OFICIAL
             // do ano correspondente para obter as parcelas a deduzir corretas.
             // Atualizando para usar a parcela a deduzir (valores de exemplo/comuns):
             return Math.max(0, imposto - parcelaDeduzir); // Garante que não seja negativo
        }
    }

    // --- Método main para Teste (Exemplo) ---
    public static void main(String[] args) {
        // Exemplo de uso
        Funcionario func1 = new Funcionario("001", "João Silva", 2, 3000.00, 1500);
        System.out.println(func1.gerarContracheque());

        Funcionario func2 = new Funcionario("002", "Maria Souza", 0, 5000.00, 2500);
        System.out.println(func2.gerarContracheque());

        Funcionario func3 = new Funcionario("003", "Carlos Lima", 1, 2000.00, 800);
        System.out.println(func3.gerarContracheque());
    }
}