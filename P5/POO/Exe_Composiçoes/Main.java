package Exe_Composiçoes;

public class Main {
    public static void main(String[] args) {
        // 1. Create instances of the "component" classes
        Data dataNascimentoJoao = new Data(15, 8, 1990);
        Endereco enderecoJoao = new Endereco("Rua das Flores", 123, "Centro", "São Paulo", "SP", "01000-000");
        Contato contatoJoao = new Contato("113333-4444", "1198888-7777", "joao.silva@email.com");

        // 2. Create an instance of Pessoa, passing the component objects
        Pessoa joaoSilva = new Pessoa(
                "João Silva",
                dataNascimentoJoao,
                enderecoJoao,
                contatoJoao
        );

        // 3. Print the Pessoa object to see all its composed parts
        System.out.println(joaoSilva);

        System.out.println("\n--- Another example ---");

        Data dataNascimentoMaria = new Data(20, 3, 1985);
        Endereco enderecoMaria = new Endereco("Avenida Principal", 45, "Jardim América", "Rio de Janeiro", "RJ", "20000-000");
        Contato contatoMaria = new Contato("", "2199999-1111", "maria.santos@example.com"); // Residential phone empty

        Pessoa mariaSantos = new Pessoa(
                "Maria Santos",
                dataNascimentoMaria,
                enderecoMaria,
                contatoMaria
        );

        System.out.println(mariaSantos);

        // Demonstrating access to composed objects:
        System.out.println("\nJoão's email: " + joaoSilva.getContato().getEmail());
        System.out.println("Maria's city: " + mariaSantos.getEndereco().getCidade());

        // In Java, when 'joaoSilva' is garbage collected, the objects it *directly* holds
        // (dataNascimentoJoao, enderecoJoao, contatoJoao) will also become eligible for garbage collection
        // if no other references point to them. This reinforces the strong "owns-a" relationship
        // characteristic of composition.
    }
}