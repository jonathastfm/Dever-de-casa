public class TabelaIRPF {

    // Valor da dedução por dependente (baseado no PDF) [cite: 5]
    private static final double VALOR_POR_DEPENDENTE = 123.00;

    // Limites das faixas da base de cálculo (baseados no PDF) [cite: 14]
    private static final double FAIXA1_LIMITE_ISENTO = 2259.20;
    private static final double FAIXA2_LIMITE = 2826.65;
    private static final double FAIXA3_LIMITE = 3751.05;
    private static final double FAIXA4_LIMITE = 4664.68;
    // (A faixa 5 é acima de FAIXA4_LIMITE)

    // Alíquotas por faixa (baseadas no PDF) [cite: 14]
    private static final double ALIQUOTA_FAIXA2 = 0.075; // 7.5%
    private static final double ALIQUOTA_FAIXA3 = 0.15;  // 15%
    private static final double ALIQUOTA_FAIXA4 = 0.225; // 22.5%
    private static final double ALIQUOTA_FAIXA5 = 0.275; // 27.5%

    // Parcelas a deduzir correspondentes a cada faixa (valores padrão comuns para essas alíquotas)
    // Estes valores NÃO estão no PDF, mas são essenciais para o cálculo correto do IRPF no Brasil.
    // É CRUCIAL verificar os valores oficiais da Receita Federal para o ano vigente.
    private static final double DEDUCAO_FAIXA2 = 169.44;
    private static final double DEDUCAO_FAIXA3 = 381.44;
    private static final double DEDUCAO_FAIXA4 = 662.77;
    private static final double DEDUCAO_FAIXA5 = 896.00;

    /**
     * Calcula o valor total da dedução por dependentes para a base de cálculo do IRPF.
     *
     * @param numeroDependentes O número de dependentes do funcionário.
     * @return O valor total a ser deduzido da base de cálculo do IRPF.
     */
    public static double calcularDescontoDependentes(int numeroDependentes) {
        return numeroDependentes * VALOR_POR_DEPENDENTE;
    }

    /**
     * Calcula o valor do desconto do IRPF com base na base de cálculo.
     * A base de cálculo é Salário Bruto - Desconto INSS - Desconto por Dependentes.
     *
     * @param baseCalculo A base de cálculo já ajustada.
     * @return O valor do imposto de renda devido.
     */
    public static double calcular(double baseCalculo) {
        double impostoDevido = 0.0;

        if (baseCalculo <= FAIXA1_LIMITE_ISENTO) {
            impostoDevido = 0.0; // Isento [cite: 14]
        } else if (baseCalculo <= FAIXA2_LIMITE) {
            impostoDevido = (baseCalculo * ALIQUOTA_FAIXA2) - DEDUCAO_FAIXA2;
        } else if (baseCalculo <= FAIXA3_LIMITE) {
            impostoDevido = (baseCalculo * ALIQUOTA_FAIXA3) - DEDUCAO_FAIXA3;
        } else if (baseCalculo <= FAIXA4_LIMITE) {
            impostoDevido = (baseCalculo * ALIQUOTA_FAIXA4) - DEDUCAO_FAIXA4;
        } else { // Acima de FAIXA4_LIMITE
            impostoDevido = (baseCalculo * ALIQUOTA_FAIXA5) - DEDUCAO_FAIXA5;
        }

        // Garante que o imposto não seja negativo (pode acontecer com bases de cálculo baixas em faixas mais altas devido à dedução)
        return Math.max(0.0, impostoDevido);
    }

    
}