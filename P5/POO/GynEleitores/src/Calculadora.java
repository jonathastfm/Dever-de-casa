package GynEleitores.src;
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

   
    
    
}