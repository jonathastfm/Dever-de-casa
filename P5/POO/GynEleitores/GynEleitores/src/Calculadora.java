/**
 * A classe Calculadora fornece funcionalidades para realizar diversas operações matemáticas.
 */
public class Calculadora {

    /**
     * Construtor padrão.
     * Esta calculadora não mantém estado, então o construtor é simples.
     */
    public Calculadora() {
        // Construtor vazio, pois não há estado para inicializar.
    }

    /**
     * Adiciona dois números.
     *
     * @param a O primeiro número.
     * @param b O segundo número.
     * @return A soma de a e b.
     */
    public double adicionar(double a, double b) {
        return a + b;
    }

    /**
     * Subtrai o segundo número do primeiro.
     *
     * @param a O primeiro número (minuendo).
     * @param b O segundo número (subtraendo).
     * @return A diferença entre a e b.
     */
    public double subtrair(double a, double b) {
        return a - b;
    }

    /**
     * Multiplica dois números.
     *
     * @param a O primeiro número.
     * @param b O segundo número.
     * @return O produto de a e b.
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide o primeiro número pelo segundo.
     *
     * @param a O dividendo.
     * @param b O divisor.
     * @return O quociente da divisão de a por b.
     * @throws IllegalArgumentException Se o divisor (b) for zero.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Erro: Divisão por zero não é permitida.");
        }
        return a / b;
    }

    /**
     * Calcula a raiz quadrada de um número.
     *
     * @param numero O número para o qual a raiz quadrada será calculada.
     * @return A raiz quadrada do número.
     * @throws IllegalArgumentException Se o número for negativo.
     */
    public double calcularRaizQuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Erro: Raiz quadrada de número negativo não é definida em números reais.");
        }
        return Math.sqrt(numero);
    }

    /**
     * Calcula o fatorial de um número inteiro não negativo.
     * O resultado é limitado pelo tipo long. Para números maiores que 20,
     * o fatorial excederá a capacidade de um long.
     *
     * @param numero O número inteiro para calcular o fatorial.
     * @return O fatorial do número.
     * @throws IllegalArgumentException Se o número for negativo ou muito grande para o tipo long.
     */
    public long calcularFatorial(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Erro: Fatorial de número negativo não é definido.");
        }
        if (numero > 20) {
            // Fatorial de 21 já excede Long.MAX_VALUE
            throw new IllegalArgumentException("Erro: O número é muito grande para calcular o fatorial com tipo long. Máximo é 20.");
        }
        if (numero == 0 || numero == 1) {
            return 1;
        }
        long resultado = 1;
        for (int i = 2; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    /**
     * Calcula a potência de uma base elevada a um expoente.
     *
     * @param base     A base da operação.
     * @param expoente O expoente da operação.
     * @return O resultado da base elevada ao expoente.
     */
    public double calcularPotencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    /**
     * Método principal para demonstrar o uso da classe Calculadora.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println("--- Operações Básicas ---");
        System.out.println("Adição (10 + 5): " + calc.adicionar(10, 5)); // 15.0
        System.out.println("Subtração (10 - 5): " + calc.subtrair(10, 5)); // 5.0
        System.out.println("Multiplicação (10 * 5): " + calc.multiplicar(10, 5)); // 50.0
        System.out.println("Divisão (10 / 5): " + calc.dividir(10, 5)); // 2.0
        System.out.println("Divisão (10 / 4): " + calc.dividir(10, 4)); // 2.5

        System.out.println("\n--- Potência e Raiz ---");
        System.out.println("Potência (2^3): " + calc.calcularPotencia(2, 3)); // 8.0
        System.out.println("Potência (5^0.5): " + calc.calcularPotencia(5, 0.5)); // Raiz de 5 ~2.236
        System.out.println("Raiz Quadrada (16): " + calc.calcularRaizQuadrada(16)); // 4.0
        System.out.println("Raiz Quadrada (2): " + calc.calcularRaizQuadrada(2)); // ~1.414

        System.out.println("\n--- Fatorial ---");
        System.out.println("Fatorial (5!): " + calc.calcularFatorial(5)); // 120
        System.out.println("Fatorial (0!): " + calc.calcularFatorial(0)); // 1
        System.out.println("Fatorial (1!): " + calc.calcularFatorial(1)); // 1
        System.out.println("Fatorial (20!): " + calc.calcularFatorial(20)); // 2432902008176640000

        System.out.println("\n--- Testes de Exceção ---");
        // Teste de divisão por zero
        try {
            System.out.println("Divisão (10 / 0): " + calc.dividir(10, 0));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // Teste de raiz quadrada de número negativo
        try {
            System.out.println("Raiz Quadrada (-4): " + calc.calcularRaizQuadrada(-4));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // Teste de fatorial de número negativo
        try {
            System.out.println("Fatorial (-1!): " + calc.calcularFatorial(-1));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // Teste de fatorial de número muito grande para long
        try {
            System.out.println("Fatorial (21!): " + calc.calcularFatorial(21));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}