package FormasGeometricas;

public class Main {
    public static void main(String[] args) {
        // Criando uma instância de Retangulo
        Retangulo retangulo = new Retangulo(5, 10);
        System.out.println("Área do Retângulo: " + retangulo.calcularArea());
        System.out.println("Perímetro do Retângulo: " + retangulo.calcularPerimetro());

        System.out.println("----------------------------------");

        // Criando uma instância de Piramide
        Piramide piramide = new Piramide(5, 10);
        System.out.println("Volume da Pirâmide: " + piramide.calcularVolume());

        System.out.println("----------------------------------");

        // Criando uma instância de Esfera
        Esfera esfera = new Esfera(5);
        System.out.println("Área da Esfera: " + esfera.calcularArea());
        System.out.println("Volume da Esfera: " + esfera.calcularVolume());
    }
    
}
