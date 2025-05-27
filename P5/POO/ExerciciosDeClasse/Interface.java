package ExerciciosDeClasse;

// Exercicio 6
interface AreaCalculavel { // [cite: 25]
    double calculaArea(); // [cite: 25]
}

class Quadrado implements AreaCalculavel { // [cite: 26]
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calculaArea() {
        return lado * lado;
    }

    @Override
    public String toString() {
        return "Quadrado de lado " + lado;
    }
}

class Circulo implements AreaCalculavel { // [cite: 26]
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    @Override
    public double calculaArea() {
        return Math.PI * raio * raio; // Usando Math.PI para o valor de pi
    }

    @Override
    public String toString() {
        return "Círculo de raio " + raio;
    }
}

class Retangulo implements AreaCalculavel { // [cite: 27]
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double calculaArea() {
        return base * altura;
    }

     @Override
    public String toString() {
        return "Retângulo de base " + base + " e altura " + altura;
    }
}

// c) Crie uma classe de Teste. [cite: 28]
class TesteAreaCalculavel {
    public static void main(String[] args) {
        // No método main crie um vetor de 5 posições que
        // contém alguns objetos do tipo AreaCalculavel. [cite: 28]
        AreaCalculavel[] formas = new AreaCalculavel[5];
        formas[0] = new Quadrado(5.0);
        formas[1] = new Circulo(3.0);
        formas[2] = new Retangulo(4.0, 6.0);
        formas[3] = new Quadrado(2.5);
        formas[4] = new Circulo(7.0);

        // Logo após, percorra esse vetor
        // imprimindo a área de cada objeto. [cite: 29]
        System.out.println("--- Teste Exercício 6 (Área Calculável) ---");
        for (AreaCalculavel forma : formas) {
            if (forma != null) {
                System.out.println("Objeto: " + forma.toString());
                System.out.println("Área: " + String.format("%.2f", forma.calculaArea()));
                System.out.println("---");
            }
        }
        System.out.println("----------------------------------------------\n");
    }
}
