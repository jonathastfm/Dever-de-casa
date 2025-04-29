public class TabelaINSS {

    // Limites das faixas de salário bruto (baseados no PDF) [cite: 13]
    private static final double FAIXA1_LIMITE = 1412.00;
    private static final double FAIXA2_LIMITE = 2666.68;
    private static final double FAIXA3_LIMITE = 4000.03;
    // (A faixa 4 é acima de FAIXA3_LIMITE)

    // Alíquotas por faixa (baseadas no PDF) [cite: 13]
    private static final double ALIQUOTA_FAIXA1 = 0.075; // 7.5%
    private static final double ALIQUOTA_FAIXA2 = 0.09;  // 9.0%
    private static final double ALIQUOTA_FAIXA3 = 0.12;  // 12.0%
    private static final double ALIQUOTA_FAIXA4 = 0.14;  // 14.0%

    /**
     * Calcula o valor do desconto do INSS de forma progressiva.
     *
     * @param salarioBruto O salário bruto do funcionário.
     * @return O valor do desconto do INSS calculado.
     */
    public static double calcular(double salarioBruto) {
        double descontoTotal = 0.0;

        // Faixa 1
        if (salarioBruto <= FAIXA1_LIMITE) {
            descontoTotal = salarioBruto * ALIQUOTA_FAIXA1;
            return descontoTotal; // Sai aqui se estiver apenas na primeira faixa
        } else {
            descontoTotal += FAIXA1_LIMITE * ALIQUOTA_FAIXA1; // Valor cheio da faixa 1
        }

        // Faixa 2
        if (salarioBruto <= FAIXA2_LIMITE) {
            double baseFaixa2 = salarioBruto - FAIXA1_LIMITE;
            descontoTotal += baseFaixa2 * ALIQUOTA_FAIXA2;
            return descontoTotal; // Sai aqui se parar na segunda faixa
        } else {
            double baseFaixa2 = FAIXA2_LIMITE - FAIXA1_LIMITE;
            descontoTotal += baseFaixa2 * ALIQUOTA_FAIXA2; // Valor cheio da faixa 2
        }

        // Faixa 3
        if (salarioBruto <= FAIXA3_LIMITE) {
            double baseFaixa3 = salarioBruto - FAIXA2_LIMITE;
            descontoTotal += baseFaixa3 * ALIQUOTA_FAIXA3;
            return descontoTotal; // Sai aqui se parar na terceira faixa
        } else {
            double baseFaixa3 = FAIXA3_LIMITE - FAIXA2_LIMITE;
            descontoTotal += baseFaixa3 * ALIQUOTA_FAIXA3; // Valor cheio da faixa 3
        }

        // Faixa 4 (Acima do limite da Faixa 3)
        double baseFaixa4 = salarioBruto - FAIXA3_LIMITE;
        descontoTotal += baseFaixa4 * ALIQUOTA_FAIXA4;

        // Pode haver um teto de contribuição do INSS, mas não foi especificado no PDF.
        // Adicionar lógica de teto aqui se necessário.

        return descontoTotal;
    }

}