package GynEleitores.src;

import java.time.Year;

/**
 * A classe Eleitor modela um eleitor com nome e ano de nascimento,
 * e determina o tipo de eleitor com base na sua idade.
 */
public class Eleitor {

    private String nome;
    private int anoNascimento;

    /**
     * Construtor da classe Eleitor.
     *
     * @param nome O nome do eleitor.
     * @param anoNascimento O ano de nascimento do eleitor.
     */
    public Eleitor(String nome, int anoNascimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    /**
     * Calcula a idade do eleitor com base no ano de nascimento e no ano atual.
     *
     * @return A idade atual do eleitor.
     */
    private int calcularIdade() {
        int anoAtual = Year.now().getValue();
        return anoAtual - this.anoNascimento;
    }

    /**
     * Verifica e retorna o tipo de eleitor com base na idade.
     * As regras são:
     * - Menor que 16 anos: "Não Eleitor"
     * - Entre 16 (inclusive) e 18 (exclusive) anos: "Eleitor Facultativo"
     * - Entre 18 (inclusive) e 65 (inclusive) anos: "Eleitor Obrigatório"
     * - Maior que 65 anos: "Eleitor Facultativo"
     *
     * @return Uma String indicando o tipo de eleitor.
     */
    public String verificarTipoEleitor() {
        int idade = calcularIdade();

        if (idade < 16) {
            return "Não Eleitor";
        } else if (idade < 18) { // Já sabemos que idade >= 16
            return "Eleitor Facultativo";
        } else if (idade <= 65) { // Já sabemos que idade >= 18
            return "Eleitor Obrigatório";
        } else { // Idade > 65
            return "Eleitor Facultativo";
        }
    }

    /**
     * Retorna o nome do eleitor.
     *
     * @return O nome do eleitor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do eleitor.
     *
     * @param nome O novo nome do eleitor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o ano de nascimento do eleitor.
     *
     * @return O ano de nascimento do eleitor.
     */
    public int getAnoNascimento() {
        return anoNascimento;
    }

    /**
     * Define o ano de nascimento do eleitor.
     *
     * @param anoNascimento O novo ano de nascimento do eleitor.
     */
    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    
}