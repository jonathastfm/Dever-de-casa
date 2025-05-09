package FormasGeometricas;

public class FormaGeometrica {
    private float base;
    private float altura;


    public FormaGeometrica(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public float getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double calcularArea() {
        return 0; // Método abstrato, deve ser implementado nas subclasses
    }
    
}
