import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        //variaveis
        Scanner scanner = new Scanner(System.in);
        String name ;
        int weight;
        double height;
        String sex;
        int IMC;

        //Entrada de dados
        System.out.println("Digite seu nome: ");
        name = scanner.nextLine();
        System.out.println("Digite seu peso: ");
        weight = scanner.nextInt();
        System.out.println("Digite sua altura: ");
        height = scanner.nextDouble();

        System.out.println("Digite seu sexo [M,F]: ");
        sex = scanner.nextLine();

        //Calculo do IMC
        IMC = (int) (weight / (height * height));

        //Saida de dados

        System.out.println("===================================");
        System.out.println("CLINICA GYN");
        System.out.println("Dados do paciente: ");
        System.out.println("Nome: " + name);
        System.out.println("Peso: " + weight);
        System.out.println("Altura: " + height);
        System.out.println("Sexo: " + sex);
        System.out.println("IMC: " + IMC);

        //Classificação do IMC
        System.out.print("Faixa de Risco: ");

        if (IMC < 18.5) {
            System.out.print("Abaixo do peso ideal");
        } else if (IMC >= 20 && IMC < 24.9) {
            System.out.print("Peso normal");
        } else if (IMC >= 24.9 && IMC <= 29.9) {
            System.out.print("Sobrepeso");
        } else if (IMC >= 30 && IMC <= 34.9) {
            System.out.print("Obesidade");
        } else if (IMC >= 35 ) {
            System.out.print("Obesidade morbida");
        } 
        System.out.print("\n");

        //peso ideal 
        if (sex == "M") {
            System.out.printf("Peso ideal: %.2f", (72.7 * height) - 58);
            
        } else if (sex == "F") {
            System.out.printf("Peso ideal: %.2f", (62.1 * height) - 44.7);
            
        } else {
            System.out.println("Sexo nao definido");
        }

    }
}
