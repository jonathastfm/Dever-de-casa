package ExerciciosDeClasse;

import java.util.Scanner;

// Exercicio 5
public class TesteExercicios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // a. Crie um assistente administrativo e um técnico. [cite: 16]
        // Imprima o número de matrícula e o nome de cada um deles. [cite: 16]
        System.out.println("--- Teste Exercício 1 ---");
        Administrativo adm = new Administrativo("Carlos Alberto", 3500, 1001, "noite", 500);
        Tecnico tec = new Tecnico("Ana Pereira", 3200, 2002, 300);

        System.out.println("\nAssistente Administrativo:");
        System.out.println("Nome: " + adm.getNome());
        System.out.println("Matrícula: " + adm.getNumeroMatricula());
        adm.exibeDados();

        System.out.println("\nAssistente Técnico:");
        System.out.println("Nome: " + tec.getNome());
        System.out.println("Matrícula: " + tec.getNumeroMatricula());
        tec.exibeDados();
        System.out.println("---------------------------\n");

        // b. Crie um animal do tipo cachorro e faça-o latir. [cite: 17]
        // Crie um gato e faça-o miar. Faça os dois animais caminharem. [cite: 17, 18]
        System.out.println("--- Teste Exercício 2a (Animais) ---");
        Cachorro dog = new Cachorro("Rex", "Labrador");
        Gato cat = new Gato("Mimi", "Siamês");

        System.out.println(dog.late()); // [cite: 17]
        System.out.println(cat.mia()); // [cite: 17]
        System.out.println(dog.caminha()); // [cite: 18]
        System.out.println(cat.caminha()); // [cite: 18]
        System.out.println("-----------------------------------\n");

        // c. Teste (como quiser) as classes Rica, Pobre e Miseravel.
        System.out.println("--- Teste Exercício 2b (Pessoas) ---");
        Rica pessoaRica = new Rica("Joana Dark", 30, 1000000.00);
        Pobre pessoaPobre = new Pobre("João Ninguém", 45);
        Miseravel pessoaMiseravel = new Miseravel("Zé da Esquina", 60);

        pessoaRica.exibeDadosPessoa();
        pessoaRica.fazCompras();
        System.out.println();

        pessoaPobre.exibeDadosPessoa();
        pessoaPobre.trabalha();
        System.out.println();

        pessoaMiseravel.exibeDadosPessoa();
        pessoaMiseravel.mendiga();
        System.out.println("------------------------------------\n");


        // d. Crie um ingresso. [cite: 19]
        // Peça para o usuário digitar 1 para normal e 2 para VIP. [cite: 19]
        // Conforme a escolha do usuário, diga se o ingresso é do tipo normal ou VIP. [cite: 20]
        // Se for VIP, peça para ele digitar 1 para camarote superior e 2 para camarote inferior. [cite: 21]
        // Conforme a escolha do usuário, diga se que o VIP é camarote superior ou inferior. [cite: 22]
        // Imprima o valor do ingresso. [cite: 23]
        System.out.println("--- Teste Exercício 3 (Ingressos) ---");
        double valorBaseIngresso = 50.0;
        double adicionalVip = 30.0;
        double adicionalCamaroteSuperior = 20.0;

        System.out.print("Digite o tipo de ingresso (1 para Normal, 2 para VIP): "); // [cite: 19]
        int tipoIngresso = scanner.nextInt();

        Ingresso ingressoComprado = null;

        if (tipoIngresso == 1) {
            ingressoComprado = new Normal(valorBaseIngresso);
            System.out.println("Ingresso selecionado: Normal"); // [cite: 20]
            ((Normal) ingressoComprado).imprimeValor(); // [cite: 23]
        } else if (tipoIngresso == 2) {
            System.out.println("Ingresso selecionado: VIP"); // [cite: 20]
            System.out.print("Digite o tipo de camarote (1 para Superior, 2 para Inferior): "); // [cite: 21]
            int tipoCamarote = scanner.nextInt();
            if (tipoCamarote == 1) {
                ingressoComprado = new CamaroteSuperior(valorBaseIngresso, adicionalVip, adicionalCamaroteSuperior);
                System.out.println("VIP selecionado: Camarote Superior"); // [cite: 22]
                ((CamaroteSuperior) ingressoComprado).imprimeValor(); // [cite: 23]
            } else if (tipoCamarote == 2) {
                ingressoComprado = new CamaroteInferior(valorBaseIngresso, adicionalVip, "Setor A10");
                System.out.println("VIP selecionado: Camarote Inferior"); // [cite: 22]
                ((CamaroteInferior) ingressoComprado).imprimeValor(); // [cite: 23]
            } else {
                System.out.println("Opção de camarote inválida. Criando VIP simples.");
                ingressoComprado = new VIP(valorBaseIngresso, adicionalVip);
                ingressoComprado.imprimeValor(); // [cite: 23]
            }
        } else {
            System.out.println("Opção de ingresso inválida.");
        }
        System.out.println("-------------------------------------\n");

        // e. Crie um imóvel. Peça para o usuário digitar 1 para novo e 2 para velho. [cite: 24]
        // Conforme a definição do usuário, imprima o valor final do imóvel. [cite: 24]
        System.out.println("--- Teste Exercício 4 (Imóveis) ---");
        double precoBaseImovel = 200000.0;
        double adicionalNovo = 50000.0;
        double descontoVelho = 30000.0;

        System.out.print("Digite o tipo do imóvel (1 para Novo, 2 para Velho): "); // [cite: 24]
        int tipoImovel = scanner.nextInt();


        if (tipoImovel == 1) {
            Novo imovelNovo = new Novo("Rua das Flores, 123", precoBaseImovel, adicionalNovo);
            System.out.println("Imóvel selecionado: Novo");
            imovelNovo.imprimePrecoFinal(); // [cite: 24]
        } else if (tipoImovel == 2) {
            Velho imovelVelho = new Velho("Avenida Principal, 789", precoBaseImovel, descontoVelho);
            System.out.println("Imóvel selecionado: Velho");
            imovelVelho.imprimePrecoFinal(); // [cite: 24]
        } else {
            System.out.println("Opção de imóvel inválida.");
        }
        System.out.println("-----------------------------------\n");

        scanner.close();
    }
}
