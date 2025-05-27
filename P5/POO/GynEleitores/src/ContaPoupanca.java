package GynEleitores.src;

/**
 * A classe ContaPoupanca modela uma conta poupança bancária.
 * Ela possui funcionalidades para depósito, saque, consulta de saldo
 * e uma regra de depósito mínimo na abertura da conta.
 */
public class ContaPoupanca {

    private String nomeCliente;
    private String numeroConta;
    private String agencia;
    private double saldo;

    private static final double DEPOSITO_MINIMO_ABERTURA = 1000.00;

    /**
     * Construtor da classe ContaPoupanca.
     * A conta só pode ser criada se o depósito inicial for igual ou superior a R$ 1.000,00.
     *
     * @param nomeCliente     O nome do titular da conta.
     * @param numeroConta     O número da conta.
     * @param agencia         A agência da conta.
     * @param depositoInicial O valor do depósito inicial.
     * @throws IllegalArgumentException Se o depósito inicial for inferior ao mínimo permitido.
     */
    public ContaPoupanca(String nomeCliente, String numeroConta, String agencia, double depositoInicial) {
        if (depositoInicial < DEPOSITO_MINIMO_ABERTURA) {
            throw new IllegalArgumentException(
                "Depósito inicial insuficiente. O valor mínimo para abertura de conta poupança é R$ " +
                String.format("%.2f", DEPOSITO_MINIMO_ABERTURA) +
                ". Valor fornecido: R$ " + String.format("%.2f", depositoInicial)
            );
        }
        this.nomeCliente = nomeCliente;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = depositoInicial; // Saldo inicial é o depósito de abertura
        System.out.println("Conta poupança para " + nomeCliente + " criada com sucesso. Saldo inicial: R$ " + String.format("%.2f", saldo));
    }

    /**
     * Deposita um valor na conta poupança.
     * O valor do depósito deve ser positivo.
     *
     * @param valor O valor a ser depositado.
     */
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido. Por favor, insira um valor positivo.");
            return;
        }
        this.saldo += valor;
        System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso. Novo saldo: R$ " + String.format("%.2f", this.saldo));
    }

    /**
     * Saca um valor da conta poupança.
     * O valor do saque deve ser positivo e não pode exceder o saldo disponível.
     *
     * @param valor O valor a ser sacado.
     * @return true se o saque foi realizado com sucesso, false caso contrário.
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido. Por favor, insira um valor positivo.");
            return false;
        }
        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para saque. Saldo atual: R$ " + String.format("%.2f", this.saldo) + ", Tentativa de saque: R$ " + String.format("%.2f", valor));
            return false;
        }
        this.saldo -= valor;
        System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso. Novo saldo: R$ " + String.format("%.2f", this.saldo));
        return true;
    }

    /**
     * Retorna o saldo atual da conta.
     *
     * @return O saldo da conta.
     */
    public double emitirSaldo() {
        System.out.println("Saldo atual da conta de " + this.nomeCliente + ": R$ " + String.format("%.2f", this.saldo));
        return this.saldo;
    }

    // Getters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getSaldo() { // Getter para o saldo, embora emitirSaldo() também o forneça
        return saldo;
    }

    // Setters (apenas para atributos que podem ser alterados após a criação)
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // numeroConta e agencia geralmente não mudam, então podem não ter setters.

    /**
     * Método principal para demonstrar o uso da classe ContaPoupanca.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        ContaPoupanca conta1 = null;
        ContaPoupanca conta2 = null;

        // Tentativa de criar conta com depósito inicial válido
        try {
            conta1 = new ContaPoupanca("Maria Joaquina", "12345-6", "001", 1500.00);
            conta1.emitirSaldo();
            conta1.depositar(500.00);
            conta1.sacar(200.00);
            conta1.sacar(2000.00); // Tentativa de sacar mais que o saldo
            conta1.sacar(-50); // Tentativa de sacar valor negativo
            conta1.depositar(-100); // Tentativa de depositar valor negativo
            conta1.emitirSaldo();

            System.out.println("\n--- Mudando nome do cliente ---");
            conta1.setNomeCliente("Maria Joaquina de Amaral Pereira");
            System.out.println("Nome do cliente atualizado: " + conta1.getNomeCliente());
            conta1.emitirSaldo();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar conta 1: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------\n");

        // Tentativa de criar conta com depósito inicial inválido
        try {
            conta2 = new ContaPoupanca("João da Silva", "98765-4", "002", 500.00); // Depósito abaixo do mínimo
            if (conta2 != null) { // Esta linha não será alcançada se a exceção for lançada
                 conta2.emitirSaldo();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar conta 2: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------\n");

        // Demonstração se conta1 foi criada
        if (conta1 != null) {
            System.out.println("Detalhes da Conta 1:");
            System.out.println("Titular: " + conta1.getNomeCliente());
            System.out.println("Conta: " + conta1.getNumeroConta());
            System.out.println("Agência: " + conta1.getAgencia());
            System.out.println("Saldo final: R$ " + String.format("%.2f", conta1.getSaldo()));
        } else {
            System.out.println("Conta 1 não foi criada devido a erro no depósito inicial.");
        }

        // Demonstração se conta2 foi criada (não deve ter sido)
        if (conta2 != null) {
            System.out.println("Detalhes da Conta 2:"); // Não deve executar esta parte
            System.out.println("Titular: " + conta2.getNomeCliente());
        } else {
            System.out.println("Conta 2 não foi criada devido ao depósito inicial ser insuficiente.");
        }
    }
}