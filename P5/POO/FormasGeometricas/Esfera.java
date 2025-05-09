package FormasGeometricas;

public class Esfera extends FormaGeometrica {
    
    private int raio;

    public Esfera(int raio) {
        super(raio, 0); // A altura não é necessária para a esfera
        this.raio = raio;
    }

    public int getRaio() {
        return raio;
    }
    public void setRaio(int raio) {
        this.raio = raio;
    }    

    public double calcularArea() {
        return 4 * Math.PI * Math.pow(getRaio(), 2);
    }

  
    public double calcularVolume() {
        
        return (4.0 / 3.0) * Math.PI * Math.pow(getRaio(), 3);
    }
}