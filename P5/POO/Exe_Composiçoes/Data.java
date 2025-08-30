package Exe_Composiçoes;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // Getters
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    // You can add setters if you need to modify the date after creation,
    // but for composition where the date is part of a person, it might be immutable.
    // For simplicity, I'm omitting setters for now.

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dia, mes, ano);
    }
}
