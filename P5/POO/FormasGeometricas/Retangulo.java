package FormasGeometricas;


public class Retangulo extends FormaGeometrica {
    
    public Retangulo(int base, int altura) {
        super(base, altura); // Chama o construtor da classe pai
    }


    public double calcularArea() {
        return getBase() * getAltura(); // Usa os getters da classe pai
    }

    public double calcularPerimetro() {
        return 2 * (getBase() + getAltura()); // Usa os getters da classe pai
    }

    
}


