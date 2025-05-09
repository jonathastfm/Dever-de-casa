package FormasGeometricas;



public class Piramide extends FormaGeometrica {
    /**
     * Construtor da classe Piramide.
     *
     * @param base   A área da base da pirâmide.
     * @param altura A altura da pirâmide.
     */
    public Piramide(int base, int altura) {
        super(base, altura); // Chama o construtor da classe pai
    }

    /**
     * Calcula o volume da pirâmide.
     * A fórmula do volume da pirâmide é: (1/3 * base * altura).
     *
     * @return O volume da pirâmide.
     */
    public double calcularVolume() {
        return (1.0 / 3.0) * getBase() * getAltura(); // Usa os getters da classe pai
    }
}