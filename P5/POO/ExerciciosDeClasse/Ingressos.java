package ExerciciosDeClasse;

// Exercicio 3
class Ingresso {
    private double valor; // valor em reais

    public Ingresso(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void imprimeValor() {
        System.out.println("Valor do Ingresso: R$" + String.format("%.2f", this.valor));
    }
}

class VIP extends Ingresso { // [cite: 6]
    private double valorAdicional;

    public VIP(double valorBase, double valorAdicional) {
        super(valorBase);
        this.valorAdicional = valorAdicional;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public double getValorVIP() { // [cite: 7]
        return super.getValor() + this.valorAdicional;
    }

    @Override
    public void imprimeValor() {
        System.out.println("Valor do Ingresso VIP: R$" + String.format("%.2f", getValorVIP()));
    }
}

class Normal extends Ingresso { // [cite: 8]
    public Normal(double valor) {
        super(valor);
    }

    public void imprimeTipo() { // [cite: 8]
        System.out.println("Ingresso Normal");
    }

    @Override
    public void imprimeValor() {
        imprimeTipo();
        super.imprimeValor();
    }
}

class CamaroteInferior extends VIP { // [cite: 9, 11]
    private String localizacaoIngresso;

    public CamaroteInferior(double valorBase, double valorAdicionalVIP, String localizacaoIngresso) {
        super(valorBase, valorAdicionalVIP);
        this.localizacaoIngresso = localizacaoIngresso;
    }

    public String getLocalizacaoIngresso() { // [cite: 9]
        return localizacaoIngresso;
    }

    public void setLocalizacaoIngresso(String localizacaoIngresso) { // [cite: 9]
        this.localizacaoIngresso = localizacaoIngresso;
    }

    public void imprimeLocalizacao() { // [cite: 9]
        System.out.println("Localização: Camarote Inferior - " + this.localizacaoIngresso);
    }

    @Override
    public void imprimeValor() {
        System.out.println("Valor do Ingresso VIP (Camarote Inferior): R$" + String.format("%.2f", getValorVIP()));
        imprimeLocalizacao();
    }
}

class CamaroteSuperior extends VIP { // [cite: 9, 11]
    private double valorAdicionalCamaroteSuperior;

    public CamaroteSuperior(double valorBase, double valorAdicionalVIP, double valorAdicionalCamaroteSuperior) {
        super(valorBase, valorAdicionalVIP);
        this.valorAdicionalCamaroteSuperior = valorAdicionalCamaroteSuperior;
    }

    public double getValorAdicionalCamaroteSuperior() {
        return valorAdicionalCamaroteSuperior;
    }

    public void setValorAdicionalCamaroteSuperior(double valorAdicionalCamaroteSuperior) {
        this.valorAdicionalCamaroteSuperior = valorAdicionalCamaroteSuperior;
    }

    public double getValorIngressoCamaroteSuperior() { // [cite: 10]
        return super.getValorVIP() + this.valorAdicionalCamaroteSuperior;
    }

    @Override
    public void imprimeValor() {
        System.out.println("Valor do Ingresso VIP (Camarote Superior): R$" + String.format("%.2f", getValorIngressoCamaroteSuperior()));
    }
}